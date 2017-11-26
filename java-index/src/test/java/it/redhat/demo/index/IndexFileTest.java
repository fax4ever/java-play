package it.redhat.demo.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;

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

		String textDirectoryPath = this.getClass().getClassLoader().getResource( "content" ).getFile();
		LOG.info( "textDirectoryPath: {}", textDirectoryPath );

		String indexDirectoryPath = textDirectoryPath.substring( 0, textDirectoryPath.lastIndexOf( "/" ) ) + "/index";
		LOG.info( "indexDirectoryPath: {}", indexDirectoryPath );

		StandardAnalyzer analyzer = new StandardAnalyzer();
		Directory indexDirectory = FSDirectory.open( Paths.get( indexDirectoryPath ) );

		// stage write

		IndexWriterConfig config = new IndexWriterConfig( analyzer );
		config.setOpenMode( IndexWriterConfig.OpenMode.CREATE );
		IndexWriter writer = new IndexWriter( indexDirectory, config );

		try {

			File[] files = new File( textDirectoryPath ).listFiles();

			for ( File file : files ) {
				Document document = new Document();
				BufferedReader br = new BufferedReader( new FileReader( file ) );

				// add content
				document.add( new TextField( "content", br ) );

				// add metadata
				document.add( new TextField( "filename", file.getName(), Field.Store.YES ) );
				document.add( new TextField( "fullpath", file.getCanonicalPath(), Field.Store.YES ) );

				writer.addDocument( document );
			}

		}
		finally {
			writer.close();
		}

		// stage read

		IndexReader reader = DirectoryReader.open( indexDirectory );
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
