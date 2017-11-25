package it.redhat.demo.index;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import static org.junit.Assert.assertEquals;

/**
 * @author Fabio Massimo Ercoli (C) 2017 Red Hat Inc.
 */
public class IndexFileTest {

	private final static Logger LOG = LoggerFactory.getLogger( IndexFileTest.class );

	@Test
	public void test() throws Exception {

		String textFilePath = this.getClass().getClassLoader().getResource( "ciao.txt" ).getFile();
		LOG.info( "textFilePath: {}", textFilePath );

		String indexDirectoryPath = textFilePath.substring( 0, textFilePath.lastIndexOf( "/" ) ) + "/index";
		LOG.info( "indexDirectoryPath: {}", indexDirectoryPath );

		StandardAnalyzer analyzer = new StandardAnalyzer();
		Directory indexDirectory = FSDirectory.open( Paths.get( indexDirectoryPath ) );

		// stage write

		IndexWriterConfig config = new IndexWriterConfig( analyzer );
		config.setOpenMode( IndexWriterConfig.OpenMode.CREATE );
		IndexWriter writer = new IndexWriter( indexDirectory, config );

		Document document = new Document();
		BufferedReader br = new BufferedReader( new FileReader( textFilePath ) );
		document.add( new TextField( "content", br ) );

		writer.addDocument( document );
		writer.close();

		// stage read

		IndexReader reader = DirectoryReader.open( indexDirectory );
		IndexSearcher searcher = new IndexSearcher( reader );
		QueryParser parser = new QueryParser( "content", analyzer);

		Query query = parser.parse( "hello" );
		TopDocs results = searcher.search( query, 5 );
		LOG.info( "Hits for hello --> {}", results.totalHits );
		assertEquals( 1, results.totalHits );

		query = parser.parse( "Hello" );
		results = searcher.search( query, 5 );
		LOG.info( "Hits for Hello --> {}", results.totalHits );
		assertEquals( 1, results.totalHits );

		query = parser.parse( "Hi there" );
		results = searcher.search( query, 5 );
		LOG.info( "Hits for Hi there --> {}", results.totalHits );
		assertEquals( 0, results.totalHits );

	}

}
