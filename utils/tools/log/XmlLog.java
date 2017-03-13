package com.lhg.sangong.utils.log;

import android.util.Log;

import com.lhg.sangong.utils.SGLog;
import com.lhg.sangong.utils.SGUtils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Created by ethan on 2016/6/7.
 * xml日志
 */
public class XmlLog {
    public static void printXml(String tag, String xml, String headString) {

        if (xml != null) {
            xml = XmlLog.formatXML(xml);
            xml = headString + "\n" + xml;
        } else {
            xml = headString + SGLog.NULL_TIPS;
        }

        SGUtils.printLine(tag, true);
        String[] lines = xml.split(SGLog.LINE_SEPARATOR);
        for (String line : lines) {
            if (!SGUtils.isEmpty(line)) {
                Log.d(tag, "║ " + line);
            }
        }
        SGUtils.printLine(tag, false);
    }

    public static String formatXML(String inputXML) {
        try {
            Source xmlInput = new StreamSource(new StringReader(inputXML));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
        } catch (Exception e) {
            e.printStackTrace();
            return inputXML;
        }
    }

}
