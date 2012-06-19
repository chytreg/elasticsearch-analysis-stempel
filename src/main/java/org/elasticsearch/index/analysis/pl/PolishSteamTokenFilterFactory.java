package org.elasticsearch.index.analysis.pl

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.stempel.StempelFilter;
import org.apache.lucene.analysis.stempel.StempelStemmer;
import org.egothor.stemmer.Trie;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettings;

public class PolishStemTokenFilterFactory extends AbstractTokenFilterFactory {
    private final StempelStemmer stemmer;
    @Inject public PolishStemTokenFilterFactory(Index index, @IndexSettings Settings indexSettings, @Assisted String name, @Assisted Settings settings) {
        super(index, indexSettings, name, settings);
        stemmer = new StempelStemmer(new Trie(true));
    }
    @Override public TokenStream create(TokenStream tokenStream) {
        return new StempelFilter(tokenStream, stemmer);
    }
}