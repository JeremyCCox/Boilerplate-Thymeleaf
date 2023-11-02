package com.example.resumegenerator.thymeleaf;

import com.itextpdf.text.pdf.BaseFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PdfGenerator {
    Logger logger = LoggerFactory.getLogger(PdfGenerator.class);


    @Autowired
    public PdfGenerator() {

    }
//    public String templateToHtml(){
//
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setPrefix("templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML");
//
//        TemplateEngine templateEngine = new TemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
//        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
//        Calendar cal = Calendar.getInstance();
//
//        Context context = new Context();
//        context.setVariable("to", "Wrap 3.0");
//
//        Path titlePath = Paths.get("src/main/resources/templates/audit_findings_template_files/image001.png");
//        String titleImageBase64 = encodeImage(titlePath);
//        String titleImage = "data:image/png;base64," + titleImageBase64;
//        context.setVariable("titleImage", titleImage);
//        context.setVariable("recFacName", "Receiver Facility");
//        context.setVariable("recFacLocation", "Location of facility");
//        context.setVariable("inspectionDate", dateFormat.format(cal.getTime()));
//
//        Path smallTitle = Paths.get("src/main/resources/templates/audit_findings_template_files/image002.png");
//        String smallTitleImageBase64 = encodeImage(smallTitle);
//        String smallTitleImage = "data:image/png;base64," + smallTitleImageBase64;
//        context.setVariable("smallTitleImage", smallTitleImage);
//        context.setVariable("auditYear", yearFormat.format(cal.getTime()));
//        context.setVariable("auditor", "Jeremy Cox");
//        context.setVariable("auditRef", "9999:001");
//
//        Path path = Paths.get("src/main/resources/templates/image.png");
//        String base64Image = encodeImage(path);
//        String image = "data:image/png;base64," + base64Image;
//        context.setVariable("image",image);
//
//        String html = templateEngine.process("thymeleaf_template.html", context);
//        return html;
//    }

    @Value("${resume.template.images}")
    private String imagePath;

    @Value("${resume.template.fonts}")
    private String fontPath;


    public String oldAuditToHtml() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("static/templates/standard/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        Context context = new Context();

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);


        String html = templateEngine.process("old_report_template.html", context);
        return html;
    }

    private List<String> formatText(String input) {
        List<String> output = new ArrayList<>(1);
        if (input != null) {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String currLine = scanner.nextLine();
                output.add(currLine);
            }
        } else {
            output.add("");
        }
        return output;
    }

    private String encodeImage(Path path) {
        byte[] imageAsBytes = new byte[0];
        try {
            Resource resource = new UrlResource(path.toUri());
            InputStream inputStream = resource.getInputStream();
            imageAsBytes = inputStream.readAllBytes();
        } catch (IOException e) {
            logger.info("\n File read Exception : \n " + e.getMessage());
        }

        return Base64.getEncoder().encodeToString(imageAsBytes);
    }

    public ByteArrayOutputStream htmlToPdf(String html) {
        try {

            ITextRenderer renderer = new ITextRenderer();
            // This line is required to add the fonts that are called in the body of template.
            // BaseFont.IDENTITY can be H or V (Horizontal or Vertical) Use H as V absolutely destroys the page (quite spectacularly).
            // BaseFont.EMBEDDED is safety, to make sure the font is saved in the pdf data (Although I think It could be removed, It worked without embedding, although I won't test further.)
            renderer.getFontResolver().addFont(fontPath + "calibri.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.setDocumentFromString(html);
            renderer.layout();
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            renderer.createPDF(baos);
            return baos;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }

    }

}