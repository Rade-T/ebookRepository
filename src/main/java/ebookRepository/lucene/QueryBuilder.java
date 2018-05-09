package ebookRepository.lucene;

import ebookRepository.dto.SearchDTO;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;

public class QueryBuilder {

    private static final int FUZZY = 1;
    private static final int PHRAZE = 2;

    public static Query buildQuery(SearchDTO dto) {
        switch (dto.getType()) {
            case FUZZY:
                return buildFuzzyQuery(dto);
            case PHRAZE:
                return buildPhraseQuery(dto);
            default:
                return buildDefaultQuery(dto);
        }
    }

    private static BooleanQuery buildFuzzyQuery(SearchDTO dto) {
        Term termTitle = new Term("title", dto.getTitle().value);
        Query queryTitle = new FuzzyQuery(termTitle);

        Term termAuthor = new Term("author", dto.getAuthor().value);
        Query queryAuthor = new FuzzyQuery(termAuthor);

        Term termKeywords = new Term("keywords", dto.getKeywords().value);
        Query queryKeywords = new FuzzyQuery(termKeywords);

        Term termContent = new Term("content", dto.getContent().value);
        Query queryContent = new FuzzyQuery(termContent);

        Term termLanguage = new Term("language", dto.getLanguage().value);
        Query queryLanguage = new FuzzyQuery(termLanguage);

        return buildBooleanQuery(queryTitle, queryAuthor, queryKeywords,
                queryContent, queryLanguage, dto);
    }

    private static BooleanQuery buildPhraseQuery(SearchDTO dto) {
        Term termTitle = new Term("title", dto.getTitle().value);
        Query queryTitle = new PhraseQuery.Builder().add(termTitle).build();

        Term termAuthor = new Term("author", dto.getAuthor().value);
        Query queryAuthor = new PhraseQuery.Builder().add(termAuthor).build();

        Term termKeywords = new Term("keywords", dto.getKeywords().value);
        Query queryKeywords = new PhraseQuery.Builder().add(termKeywords).build();

        Term termContent = new Term("content", dto.getContent().value);
        Query queryContent = new PhraseQuery.Builder().add(termContent).build();

        Term termLanguage = new Term("language", dto.getLanguage().value);
        Query queryLanguage = new PhraseQuery.Builder().add(termLanguage).build();

        return buildBooleanQuery(queryTitle, queryAuthor, queryKeywords,
                queryContent, queryLanguage, dto);
    }

    private static BooleanQuery buildDefaultQuery(SearchDTO dto) {
        Term termTitle = new Term("title", dto.getTitle().value);
        Query queryTitle = new TermQuery(termTitle);

        Term termAuthor = new Term("author", dto.getAuthor().value);
        Query queryAuthor = new TermQuery(termAuthor);

        Term termKeywords = new Term("keywords", dto.getKeywords().value);
        Query queryKeywords = new TermQuery(termKeywords);

        Term termContent = new Term("content", dto.getContent().value);
        Query queryContent = new TermQuery(termContent);

        Term termLanguage = new Term("language", dto.getLanguage().value);
        Query queryLanguage = new TermQuery(termLanguage);

        return buildBooleanQuery(queryTitle, queryAuthor, queryKeywords,
                queryContent, queryLanguage, dto);
    }


    private static BooleanQuery buildBooleanQuery(Query queryTitle,
                                                  Query queryAuthor,
                                                  Query queryKeywords,
                                                  Query queryContent,
                                                  Query queryLanguage,
                                                  SearchDTO dto) {
        return new BooleanQuery.Builder()
                .add(new BooleanClause(queryTitle, dto.getTitle().occur))
                .add(new BooleanClause(queryAuthor, dto.getAuthor().occur))
                .add(new BooleanClause(queryKeywords, dto.getKeywords().occur))
                .add(new BooleanClause(queryContent, dto.getContent().occur))
                .add(new BooleanClause(queryLanguage, dto.getLanguage().occur))
                .build();
    }
}
