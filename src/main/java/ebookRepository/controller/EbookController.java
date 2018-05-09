package ebookRepository.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ebookRepository.lucene.Indexer;
import ebookRepository.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ebookRepository.dto.EbookDTO;
import ebookRepository.model.Ebook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/ebooks")
public class EbookController {

    private static String UPLOAD_FOLDER = "src/main/resources/files/";

	@Autowired
	private EbookService ebookService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EbookDTO>> getEbooks() throws IOException {

		List<Ebook> ebooks = ebookService.findAll();
		List<EbookDTO> ebooksDTO = new ArrayList<>();
		for (Ebook ebook : ebooks) {
		    Indexer.addFileToIndex(ebook);
			ebooksDTO.add(new EbookDTO(ebook));
		}

		return new ResponseEntity<>(ebooksDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EbookDTO> getEbook(@PathVariable Long id) {
		Ebook ebook = ebookService.findOne(id);
		if (ebook == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		EbookDTO ebookDTO = new EbookDTO(ebook);
		return new ResponseEntity<>(ebookDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<EbookDTO> saveEbook(@RequestBody EbookDTO ebookDTO) {
		Ebook ebook = new Ebook();
		ebook.setTitle(ebookDTO.getTitle());
		ebook.setAuthor(ebookDTO.getAuthor());
		ebook.setKeywords(ebookDTO.getKeywords());
		ebook.setFilename(ebookDTO.getFilename());
		ebook.setMIME(ebookDTO.getMIME());
		ebook.setPublicationYear(ebookDTO.getPublicationYear());
		ebook.setCataloguer(userService.findOne(ebookDTO.getCataloguer()));
		ebook.setCategory(categoryService.findOne(ebookDTO.getCategory()));
		ebook.setLanguage(languageService.findOne(ebookDTO.getLanguage()));
		ebookService.save(ebook);
		return new ResponseEntity<>(new EbookDTO(ebook), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.POST)
	public ResponseEntity<EbookDTO> updateEbook(@RequestBody EbookDTO ebookDTO, @PathVariable Long id) throws IOException {
		Ebook ebook = new Ebook();
		ebook.setId(id);
		ebook.setTitle(ebookDTO.getTitle());
		ebook.setAuthor(ebookDTO.getAuthor());
		ebook.setKeywords(ebookDTO.getKeywords());
		ebook.setFilename(ebookDTO.getFilename());
		ebook.setMIME(ebookDTO.getMIME());
        ebook.setPublicationYear(ebookDTO.getPublicationYear());
		ebook.setCataloguer(userService.findOne(ebookDTO.getCataloguer()));
		ebook.setCategory(categoryService.findOne(ebookDTO.getCategory()));
		ebook.setLanguage(languageService.findOne(ebookDTO.getLanguage()));
		ebookService.save(ebook);
        Indexer.addFileToIndex(ebook);
		return new ResponseEntity<>(new EbookDTO(ebook), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		ebookService.delete(id);

		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}

//    @RequestMapping(value = "/files/{filename:.+}", method=RequestMethod.GET)
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file) {

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/downloadFile/{filename}", method = RequestMethod.GET)
    public StreamingResponseBody getFile(@PathVariable("filename") String filename) throws IOException {
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=\"demo.pdf\"");

        String fileLocation = UPLOAD_FOLDER + filename + ".pdf";
        InputStream inputStream = new FileInputStream(new File(fileLocation));
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
        };
    }
}