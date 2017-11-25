package it.redhat.demo.index;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

/**
 * @author Fabio Massimo Ercoli (C) 2017 Red Hat Inc.
 */
public class IndexService {

	private Directory directory;

	public IndexService() {
		directory = new RAMDirectory();
	}



}
