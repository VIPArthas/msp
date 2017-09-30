package place;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;
/**
 * PDF转word
 */
public class TestPDFBox {

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\AppTestData\\第一节 接案（2）.pdf");
		PDDocument doc = PDDocument.load(file);
		int pagenumber = doc.getNumberOfPages();
		System.out.print("pages" + pagenumber);
		FileOutputStream fos = new FileOutputStream("D:\\AppTestData\\第一节 接案（2）.doc");
		Writer writer = new OutputStreamWriter(fos, "UTF-8");
		/*PDFTextStripper stripper = new PDFTextStripper();
		stripper.setSortByPosition(true);// 排序
		stripper.setStartPage(3);// 设置转换的开始页
		stripper.setEndPage(7);// 设置转换的结束页
		stripper.writeText(doc, writer);
		writer.close();
		doc.close();*/
	}
}