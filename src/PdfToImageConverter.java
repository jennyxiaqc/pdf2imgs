import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PdfToImageConverter {

	public static void main(String[] args) throws IOException {
		try {
			String destinationDir = "C:\\flyerCreate\\images/";

			File sourceFile = new File("C:\\flyerCreate\\images\\sample.pdf");
			File destinationFile = new File(destinationDir);

			if (!destinationFile.exists()) {
				destinationFile.mkdir();
				System.out.println("Folder Created -> " + destinationFile.getAbsolutePath());
			}

			if (sourceFile.exists()) {
                            try (PDDocument document = PDDocument.load(sourceFile)) {
                                PDFRenderer pdfRenderer = new PDFRenderer(document);
                                
                                String fileName = sourceFile.getName().replace(".pdf", "");
                                
                                // int pageNumber = 0;
                                
                                // for (PDPage page : document.getPages()) {
                               int pages=document.getNumberOfPages();
                                for (int pageNumber = 0; pageNumber < pages; ++pageNumber) {
                                    BufferedImage bim = pdfRenderer.renderImage(pageNumber);
                                    
                                    //String destDir = destinationDir + fileName + "_" + pageNumber + ".png";
                                    String destDir = destinationDir  + (pageNumber+1) + ".png";
                                    ImageIO.write(bim, "png", new File(destDir));
                                 }
                                 System.out.println(pages+" Image saved at -> " + destinationFile.getAbsolutePath());

                            }

			} else {
				System.err.println(sourceFile.getName() + " File does not exist");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}