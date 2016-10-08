package com.cmp.demo;

/**
 * Created by breez on 2016/03/30.
 */

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;


public class IndexWriterDemo {

    public static void main(String[] args) throws IOException, ParseException {
        readIndex("D:/workspace/javadev-ideas/study/lucenedemo/target/lucene");
        readAllIndexDocs("D:/workspace/javadev-ideas/study/lucenedemo/target/lucene");
    }

    public static void createIndex(String index_file_path) {

        IndexWriter writer = null;

        try {
            /*Directory directory = new RAMDirectory();*/
            Directory directory = FSDirectory.open(Paths.get(index_file_path));
            Analyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            writer = new IndexWriter(directory, iwc);
            Document document = new Document();
            document.add(new StringField("addr", "北京市朝阳区惠新西街5号院1单元1205室", Field.Store.YES));
            writer.addDocument(document);
            document = new Document();
            document.add(new StringField("addr", "湖北省武汉市武昌区中山路29号", Field.Store.YES));
            writer.addDocument(document);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }


    public static void readIndex(String index_file_path) throws IOException, ParseException {
        Analyzer a = new StandardAnalyzer();
        Directory dir = FSDirectory.open(Paths.get(index_file_path));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher is = new IndexSearcher(reader);
        QueryParser parser = new QueryParser("武汉", a);
        Query query = parser.parse("武汉");
        TopDocs topDocs = is.search(query, 1000);
        System.out.println("总共匹配多少个：" + topDocs.totalHits);
        ScoreDoc[] hits = topDocs.scoreDocs;
        System.out.println("多少条数据：" + hits.length);
        for (ScoreDoc scoreDoc : hits) {
            System.out.println("匹配得分：" + scoreDoc.score);
            System.out.println("文档索引ID：" + scoreDoc.doc);
            Document document = is.doc(scoreDoc.doc);
            System.out.println(document.get("info"));
        }
        reader.close();
        dir.close();
    }

    public static void readAllIndexDocs(String index_file_path)
            throws IOException {
        try {
            Directory dir = FSDirectory.open(Paths.get(index_file_path));
            IndexReader reader = DirectoryReader.open(dir);
            IndexSearcher searcher = new IndexSearcher(reader);
            reader.maxDoc();
            Document doc = null;
            for (int i = 0; i < reader.maxDoc(); i++) {
                doc = searcher.doc(i);
                System.out.println("Doc [" + i + "] : addr: " + doc.get("addr") + ", modified: " + doc.get("modified"));
            }
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}