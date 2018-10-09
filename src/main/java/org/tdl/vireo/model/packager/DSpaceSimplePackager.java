package org.tdl.vireo.model.packager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.zip.ZipEntry;
//import java.util.zip.FileOutputStream;

import javax.persistence.Entity;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.tdl.vireo.model.FieldValue;
import org.tdl.vireo.model.Submission;
import org.tdl.vireo.model.export.DSpaceSimplePackage;
import org.tdl.vireo.model.formatter.AbstractFormatter;
import org.tdl.vireo.service.AssetService;

@Entity
public class DSpaceSimplePackager extends AbstractPackager<DSpaceSimplePackage> {

    public DSpaceSimplePackager() {

    }

    public DSpaceSimplePackager(String name) {
        setName(name);
    }

    public DSpaceSimplePackager(String name, AbstractFormatter formatter) {
        this(name);
        setFormatter(formatter);
    }

    // @Override
    // public DSpaceSimplePackage packageExport(Submission submission, String
    // manifest){
    // }

    @Override
    public DSpaceSimplePackage packageExport(Submission submission, Map<String, String> ds_docs) {

        String packageName = "submission-" + submission.getId() + "-";

        // Add templated files
        List<File> pkg_list = new ArrayList<File>();
        try {
            // Add non submitted content
            for (Map.Entry<String, String> ds_entry : ds_docs.entrySet()) {
                String docName = ds_entry.getKey();
                String docContents = ds_entry.getValue();
                File ff = File.createTempFile(docName, "");
                FileUtils.writeStringToFile(ff, docContents, "UTF-8");
                pkg_list.add(ff);
            }
            

            /***
             * //Add submitted content String contentsFileName = "contents"; File
             * contentsFile = File.createTempFile(contentsFileName, null);
             * System.out.println("AAAA"); List<FieldValue> documentFieldValuesForContent =
             * submission.getAllDocumentFieldValues(); for (FieldValue documentFieldValue :
             * documentFieldValuesForContent) { System.out.println("FKEY FFF
             * "+documentFieldValue.getFileName()+" FFF
             * "+submission.getPrimaryDocumentFieldValue().getFieldPredicate().getValue());
             * FileUtils.writeStringToFile(contentsFile,documentFieldValue.getFileName()+"\tbundle:CONTENT",
             * "UTF-8"); System.out.println("BBBB
             * "+documentFieldValue.getFieldPredicate().getValue().equals("_doctype_primary"));
             * }
             * 
             * // Add manifest to zip //fos.putNextEntry(new
             * FileOutputStream(contentsFileName));
             * fos.write(Files.readAllBytes(contentsFile.toPath())); //fos.closeEntry();
             * 
             * contentsFile.delete();
             ***/

            /***
             * List<FieldValue> documentFieldValues =
             * submission.getAllDocumentFieldValues(); for (FieldValue documentFieldValue :
             * documentFieldValues) {
             * 
             * // TODO: add file whitelist for publish
             * 
             * File exportFile = getAbsolutePath(documentFieldValue.getValue()).toFile();
             * 
             * //fos.putNextEntry(new ZipEntry(documentFieldValue.getFileName()));
             * fos.write(Files.readAllBytes(exportFile.toPath())); //fos.closeEntry();
             * 
             * } fos.close();
             ***/

        } catch (IOException ioe) {
            throw new RuntimeException("Unable to generate package", ioe);
        }

        return new DSpaceSimplePackage(submission, "http://purl.org/net/sword-types/METSDSpaceSIP", pkg_list);
    }

}
