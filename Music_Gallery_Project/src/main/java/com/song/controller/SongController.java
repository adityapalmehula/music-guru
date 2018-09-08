package com.song.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.lang.model.element.Element;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFSDBFile;
import com.mymusic.Mymusic;
import com.register.Register;
import com.song.repository.Songrepository;
import com.songlist.Song;

import javazoom.jl.player.Player;

@RestController
@RequestMapping("/musicguru")
public class SongController {
	Long currentFrame;
	Clip clip;
	AudioInputStream audioInputStream;
	String filePath;

	private Songrepository songrepository;

	public SongController(Songrepository songrepository) {
		this.songrepository = songrepository;
	}

	@GetMapping("/all")
	public List<Song> getAll() {
		return songrepository.findAll();
	}

	// ........ upload method for image
	@PostMapping("/file/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		String uploadPath = "src/main/resources/images/";
		File path = new File(uploadPath);
		if (!path.exists())
			path.mkdirs();
		System.out.println(uploadPath);
		try {
			Files.copy(file.getInputStream(), Paths.get(uploadPath, file.getOriginalFilename()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "upload successfully ";
	}
	// ........ close of upload image

	// ........ upload method for audio

	@PostMapping("/file/upload/audio")
	public String uploadaudio(@RequestParam("file") MultipartFile file) {
		String uploadPath = "src/main/resources/audios/";
		File path = new File(uploadPath);
		if (!path.exists())
			path.mkdirs();
		System.out.println(uploadPath);
		try {
			Files.copy(file.getInputStream(), Paths.get(uploadPath, file.getOriginalFilename()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Upload Audio is succesfully";

	}

	// ........ upload method for close

	// .........get image Api

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) throws IOException {
		System.out.println(id);
		ClassPathResource imgFile = new ClassPathResource("images/" + id + ".jpg");
		byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);

	}
	// //........Close get image
// search the song 
	// to search venue by name in mongodb database
		@RequestMapping(value = "/songs/search/name={name}", method = RequestMethod.GET)
		public ResponseEntity<List<Song>> searchbyName(@PathVariable String name) {
			return ResponseEntity.ok().body(songrepository.findBysongnameLike(name));
		}
		
	// ......... Api to play song
	@RequestMapping(value = "/playsong/{id}", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
	public void doGet(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) throws ServletException, IOException {
		System.out.println("Request fot mp3");
		String fileName =  id + ".mp3";
		ServletOutputStream stream = null;
		BufferedInputStream buf = null;
		try {

			stream = response.getOutputStream();
			File mp3 = new File(
					"E:\\1- kls office\\Aditya music guru\\Myfirstofficeproject\\Music_Gallery_Project\\src\\main\\resources\\audios\\"
							+ fileName);
			// set response headers
			response.setContentType("audio/mpeg");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			response.setContentLength((int) mp3.length());
			FileInputStream input = new FileInputStream(mp3);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			// read from the file; write to the ServletOutputStream
			while ((readBytes = buf.read()) != -1)
				stream.write(readBytes);
		} catch (IOException ioe) {
			throw new ServletException(ioe.getMessage());
		} finally {
			if (stream != null)
				stream.close();
			if (buf != null)
				buf.close();
		}
	}

// pause the song ............
	@RequestMapping(value = "/pause", method = RequestMethod.GET)
	@ResponseBody
	public void pause() {
		try {

			this.currentFrame = this.clip.getMicrosecondPosition();
			clip.stop();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

// stop the song .................

	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	@ResponseBody
	public void stop() {
		try {
			clip.stop();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

// ......... close the play song
	@RequestMapping(value = "/songs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody Song song) {
		try {

			songrepository.save(song);
			return ResponseEntity.noContent().build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

// ....................................

// how to donload the Song.......................
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void download(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) {
		ServletOutputStream stream = null;
		BufferedInputStream buf = null;
		try {
			// my mp3 path to file
			String pathToFile = "E:/1- kls office/Aditya music guru/Myfirstofficeproject/Music_Gallery_Project/src/main/resources/audios/"+id;
			stream = response.getOutputStream();
			File mp3 = new File(pathToFile);
			// header to force download
			response.setContentType("application/force-download");
			response.setContentLength((int) mp3.length());
			FileInputStream input = new FileInputStream(mp3);
			buf = new BufferedInputStream(input);
			int readBytes = 0;
			while ((readBytes = buf.read()) != -1)
				stream.write(readBytes);
		} catch (IOException ioe) {
			
		} finally {
			
		}
	}

// --------- this closing of this class .
}
