package it.redhat.demo.index;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.RAMDirectory;

import static org.junit.Assert.assertEquals;

/**
 * @author Fabio Massimo Ercoli (C) 2017 Red Hat Inc.
 */
public class IndexTest {

	private final static Logger LOG = LoggerFactory.getLogger( IndexTest.class );

	@Test
	public void test() throws Exception {

		StandardAnalyzer analyzer = new StandardAnalyzer();
		RAMDirectory directory = new RAMDirectory();

		IndexWriterConfig writerConfig = new IndexWriterConfig( analyzer );
		IndexWriter writer = new IndexWriter( directory, writerConfig );

		Document document = new Document();
		document.add( new TextField( "content", "hello world", Field.Store.YES ) );
		writer.addDocument( document );
		document.add( new TextField( "content", "hello people", Field.Store.YES ) );
		writer.addDocument( document );
		writer.close();

		IndexReader reader = DirectoryReader.open( directory );
		IndexSearcher searcher = new IndexSearcher( reader );
		QueryParser parser = new QueryParser( "content", analyzer );

		Query query = parser.parse( "hello" );
		TopDocs results = searcher.search( query, 5 );
		LOG.info( "Hits for hello --> {}", results.totalHits );
		assertEquals( 2, results.totalHits );

		query = parser.parse( "Hello" );
		results = searcher.search( query, 5 );
		LOG.info( "Hits for Hello --> {}", results.totalHits );
		assertEquals( 2, results.totalHits );

		query = parser.parse( "Hi there" );
		results = searcher.search( query, 5 );
		LOG.info( "Hits for Hi there --> {}", results.totalHits );
		assertEquals( 0, results.totalHits );

	}

}
