using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Office.Interop.Word;
using System.Reflection;

namespace TestForOffice
{
    class Program
    {
        static void Main(string[] args)
        {
            Object path;
            //文件路径变量        
            string strContent;
            //文本内容变量    
            Application wordApp;
            //Word应用程序变量   
            Document wordDoc;
            //Word文档变量          
            path = @"C:\Users\ylw\Desktop\MyWord.doc";
            //路径      
            wordApp = new Application();
            //初始化      
            //如果已存在，则删除     
            if (File.Exists((string)path))
            {
                File.Delete((string)path);
            }
            //由于使用的是COM库，因此有许多变量需要用Missing.Value代替 
            Object Nothing = Missing.Value;
            wordDoc = wordApp.Documents.Add(ref Nothing, ref Nothing, ref Nothing, ref Nothing);
            strContent = "使用C#向Word文档中写入文本\n";
            wordDoc.Paragraphs.Last.Range.Text = strContent;
            strContent = "写入第二行文本";
            wordDoc.Paragraphs.Last.Range.Text = strContent;
            //WdSaveFormat为Word 2007文档的保存格式      
            object format = WdSaveFormat.wdFormatDocumentDefault;
            //将wordDoc文档对象的内容保存为DOCX文档   
            wordDoc.SaveAs(ref path, ref format, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing);
            //关闭wordDoc文档对象    
            wordDoc.Close(ref Nothing, ref Nothing, ref Nothing);
            //关闭wordApp组件对象      
            wordApp.Quit(ref Nothing, ref Nothing, ref Nothing);
            Console.WriteLine(path + " 创建完毕！");

            path = @"C:\Users\ylw\Desktop\Microsoft_Office_Word_97_-_2003___1.doc";
            WordHelper helper = new WordHelper();
            bool result = helper.OpenAndActive((string)path, true, true);
            Console.WriteLine("result is " + result);

            //WdSaveFormat为Word 2007文档的保存格式      
              format = WdSaveFormat.wdFormatFilteredHTML;
            //将wordDoc文档对象的内容保存为DOCX文档   
            path = @"C:\Users\ylw\Desktop\Microsoft_Office_Word_97_-_2003___1.html";
            helper.WordDocument.SaveAs(ref path, ref format, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing);
            helper.Close();
            Console.ReadKey();
        }
    }
}
