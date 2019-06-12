package home.olse.hasnew.apps;

import home.olse.hasnew.VersionedAppsImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Opera extends VersionedAppsImpl {
    {
        name = "Opera";
    }

    @Override
    public String getFileMask() {
        return "DPD Opera ";
    }

    @Override
    public String getURL() {
        return "https://download4.operacdn.com/pub/opera/desktop/";
    }

    @Override
    public void reReadData() throws IOException {
        String excludeString = "../";
        Document doc = Jsoup.connect(getURL()).get();
        Elements els = doc.getElementsByTag("a");
        Version v = new Version("00.0.0000.00");
        for (Element a : els) {
            if (a.text() != null && a.text().endsWith("/")) {
                if (!a.text().contains(excludeString)) {
                    Version cursor = new Version(a.text().substring(0, a.text().length() - 1));
                    if (cursor.compareTo(v) > 0) {
                        v = cursor;
                    }
                }
            }
        }
        version = v.toString();

    }

    private static class Version implements Comparable<Version> {
        private int v1;
        private int v2;
        private int v3;
        private int v4;


        private Version(String version) {
//            50.0.2762.45
            if (!version.contains(".") || version.split("\\.").length != 4) {
                throw new IllegalArgumentException("Version should be like 50.0.2762.45");
            }
            String[] ss = version.split("\\.");
            v1 = Integer.valueOf(ss[0]);
            v2 = Integer.valueOf(ss[1]);
            v3 = Integer.valueOf(ss[2]);
            v4 = Integer.valueOf(ss[3]);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Version)) {
                return false;
            }
            Version o = (Version) obj;
            return v1 == o.v1 && v2 == o.v2 && v3 == o.v3 && v4 == o.v4;
        }

        @Override
        public int compareTo(Version o) {
            int dv1 = v1 - o.v1;
            int dv2 = v2 - o.v2;
            int dv3 = v3 - o.v3;
            int dv4 = v4 - o.v4;
            return dv1 != 0 ? dv1 :
                    (dv2 != 0 ? dv2 :
                            (dv3 != 0 ? dv3 :
                                    dv4
                            )
                    );
        }

        @Override
        public String toString() {
            return v1 + "." + v2 + "." + v3 + "." + v4;
        }
    }


}
