package webapp.serializeStrategy;

import webapp.model.*;
import webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamStrategy implements Strategy {
    private XmlParser xmlParser;

    public XmlStreamStrategy() {
        xmlParser = new XmlParser(
                Resume.class, Organization.class,
                OrganizationListSection.class, TextSection.class, ListSection.class, Organization.Position.class);
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(r, w);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}
