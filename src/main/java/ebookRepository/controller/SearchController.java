package ebookRepository.controller;

import ebookRepository.dto.EbookDTO;
import ebookRepository.dto.SearchDTO;
import ebookRepository.lucene.QueryBuilder;
import ebookRepository.lucene.ResultRetriever;
import ebookRepository.service.EbookService;

import org.apache.lucene.search.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
	
	@Autowired
	private EbookService ebookService;

    @PostMapping
    public ResponseEntity<?> search(@RequestBody SearchDTO dto) {
        Query booleanQuery = QueryBuilder.buildQuery(dto);

        ResultRetriever rr = new ResultRetriever();
        List<EbookDTO> ebooks = rr.printSearchResults(booleanQuery);
        List<EbookDTO> results = new ArrayList<>();
        for (EbookDTO ebookDTO : ebooks) {
			results.add(new EbookDTO(ebookService.findOne(ebookDTO.getId())));
		}

        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
