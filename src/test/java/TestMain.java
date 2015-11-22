import com.cn.wa000.framework.util.HttpClientUtils;


public class TestMain
{
    public static void main(String[] args)
    {
        String resultHtml = HttpClientUtils.runWithGit("http://www.ip5.me/");
        String matchOne = "<td height=\"30\"><div id=\"ip_addr\" style=\"color:#191970\">";
        String matchTwo = "</div></td>";
        String ip = resultHtml.split(matchOne)[1].split(matchTwo)[0];
        System.out.println(ip);
    }
}
