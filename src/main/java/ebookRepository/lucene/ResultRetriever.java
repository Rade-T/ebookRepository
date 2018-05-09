package ebookRepository.lucene;

import ebookRepository.model.Category;
import com.google.gson.Gson;
import ebookRepository.dto.EbookDTO;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ebookRepository.lucene.Constants.luceneDir;

public class ResultRetriever {

    private TopScoreDocCollector collector;

    public ResultRetriever() {
        collector = TopScoreDocCollector.create(10);
    }

    public List<EbookDTO> printSearchResults(Query query) {
        List<EbookDTO> ebooks = new ArrayList<>();

        try {
            Directory fsDir = new SimpleFSDirectory(luceneDir);
            DirectoryReader reader = DirectoryReader.open(fsDir);
            IndexSearcher is = new IndexSearcher(reader);
            is.search(query, collector);

            ScoreDoc[] hits = collector.topDocs().scoreDocs;
            System.err.println("Found " + hits.length + " document(s) that matched query '" + query + "':");

            for (int i = 0; i < collector.getTotalHits(); i++) {
                int docId = hits[i].doc;
                Document doc = is.doc(docId);

                Category category = new Gson().fromJson(doc.get("category"), Category.class);
                EbookDTO dto = new EbookDTO();
                dto.setId(Integer.parseInt(doc.get("id")));
                dto.setTitle(doc.get("title"));
                dto.setAuthor(doc.get("author"));
                dto.setPublicationYear(doc.get("publicationYear"));
                dto.setCategory(category.getId());
//                EbookDto ebookDto = new EbookDto(
//                        Integer.parseInt(doc.get("id")),
//                        doc.get("title"),
//                        doc.get("author"),
//                        Integer.parseInt(doc.get("publicationYear")),
//                        category
//                );

                ebooks.add(dto);
            }

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return ebooks;
    }

}
