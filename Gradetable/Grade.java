import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Sort{						//归并排序，时间复杂度O(N*logN)
	public int[]sort(int []numbers,int low,int high){
	
		int  mid=(low+high)/2;	//中间值
		
		if(low<high){
			
		sort(numbers,low,mid);	//排左边
		
		sort(numbers,mid+1,high);	//排右边
		
		merge(numbers,low,mid,high);	//归并排序
		}
		return numbers;
	}
	public void merge(int []nums,int low,int mid,int high){
		
		int []temp=new int[high-low+1];		//临时数组，用来传值
		
		int i=low;		//左指针
		int j=mid+1;	//右指针
		int k=0;	
		
		while(i<=mid&&j<=high){
									//先将较大的数移到新数组中
			if(nums[i]>nums[j]){
				temp[k++]=nums[i++];
			}
			else{
				temp[k++]=nums[j++];
			}
		}
		while(j<=high){			//将右边剩余的数移到数组中
			temp[k++]=nums[j++];
		}
		
		while(i<=mid){			//将左边剩余的数移到数组中
			temp[k++]=nums[i++];
		}
		
		for(int m=0;m<temp.length;m++){		//把新数组中的数覆盖nums数组
			nums[m+low]=temp[m];
		}
	}
}
public class Grade {
	public static ArrayList Gradeslist=new ArrayList();		//创建一个ArrayList，用来动态添加数据
	public static BufferedWriter bufferedWriter;		 //文本输出流
	public static BufferedReader bufferedReader;		 //文本读入流
	public static InputStreamReader read;				//字符输入流
	public static void reading(String filepath){
		try{
		String ecoding="GBK";			
		File file=new File(filepath);			//创建一个新File类的对象file
		if(file.isFile()&&file.exists()){			//判断文件是否存在是否是标准文件
			read=new InputStreamReader(new FileInputStream(file),ecoding);	//把file读入内存并解读
			bufferedReader=new BufferedReader(read);}		//通过bufferedReader方法转换成I/O可以识别的数据
		else{
			System.out.println("找不到指定的文件！");
		}
	}
		catch(Exception e){					//捕获异常
			System.out.println("读取文件内容出错！");
			e.printStackTrace();
		}
	}
	public static void readOriginalRecords(String filepath){		//读成绩单初始数据的方法
		try{
				reading(filepath);		//读数据
				
				String [][]messagerecord=new String[6][7];		//创建messagerecord二维数组（已知成绩单中数据大小）
				
				for(int i=0;i<6;i++){
	
				messagerecord[i]=bufferedReader.readLine().split("  ");		//将每行数据传到messagerecord[i]中去
				
				Gradeslist.add(messagerecord[i]);}		//将数组messagerecord[i]添加到GradeList中
				
				read.close();		//结束读入
		}
		catch(Exception e){				//捕获异常
			System.out.println("读取文件内容出错！");
			e.printStackTrace();
		}
	}
	public static int getlistnumbers(String filepath) throws IOException{		//获取文件中文本行数的方法
		
		reading(filepath);		//读数据
		
		 String strLine = bufferedReader.readLine();	//strline是每行的文本
	        int totalLines = 0;
	        while (strLine != null) {
	            strLine = bufferedReader.readLine();
	            totalLines++;
	        }
		return totalLines;		//返回行数
	}
	public static void readnamelist(String filepath,int listnumbers){		//读名册的方法
		try{
			reading(filepath);		//读数据
			
			String [][]namelistrecord=new String[listnumbers][2];	//根据行数创建二维数组
			
			for(int m=0;m<listnumbers;m++){
				
				namelistrecord[m]=bufferedReader.readLine().split("  ");	//将每行数据传到namelistrecord[i]中去
			}
			
			String [][]messagerecord=new String[listnumbers][7];	//根据行数创建完整数据的二维数组
			
			for(int k=0;k<listnumbers;k++){			//传数据
				for(int f=0;f<7;f++){
					if(f<2){
						messagerecord[k][f]=namelistrecord[k][f];
					}else{
						messagerecord[k][f]=String.valueOf(randomnumber());		//调用随机数方法
					}
				}
				Gradeslist.add(messagerecord[k]);		//将数组messagerecord[i]添加到GradeList中
			}
			read.close();			//结束读入
		}
		catch(Exception e){		//捕获异常
			System.out.println("读取文件内容出错！");
			e.printStackTrace();
		}
	}
	public static void printGradesBook() throws IOException{
		
		int [][]gradebook=grades();		//调用grades方法初始化数组
		
		for(int j=0;j<Gradeslist.size();j++){				//输出GradesBook
			
			String[] d=(String[])(Gradeslist.get(j));	
			
			for(int i=0;i<7;i++){
				bufferedWriter.write(d[i]+" ");	}
			
			if(j==0){
			bufferedWriter.write("总成绩 ");}
			
			if(j>0){
				bufferedWriter.write(String.valueOf(totalgrades(gradebook[j-1])));		//调用totalgrades方法
			}
			bufferedWriter.newLine();		//换行
	}
		bufferedWriter.write("平均成绩： ");
		getAverage(gradebook);		//调用getAverage方法
	}
	public static int randomnumber(){				//生成随机数方法
		
		int rnum=(int) (Math.sqrt(169)*(new Random().nextGaussian())+75);		//生成正态分布随机数
		return rnum;
	}
	public static int[][] grades(){				//获取成绩数据方法
		
		int [][]grade=new int[Gradeslist.size()-1][5];		//根据Gradeslist里的存储结构创建数组
		
		for(int i=1;i<Gradeslist.size();i++)
			{String[] d=(String[])(Gradeslist.get(i));		//获取每行数据
			for(int j=0;j<5;j++){
				grade[i-1][j]=Integer.parseInt(d[j+2]);			//将对应的字符串转换为整形
			}
		}
		return grade;
	}
	public static void getAverage(int [][]setofgrades) throws IOException	//获取成绩平均值的方法
	{
		int total=0;
		double Average=0;
		
		for(int i=0;i<5;i++){				//计算总和
			for(int j=0;j<setofgrades.length;j++){
				total+=setofgrades[j][i];
			}
			Average=(double)total/setofgrades.length;		//求平均值
			
			String average=String.format("%.2f", Average);		//转化为保留两位小数的字符串格式
			
			bufferedWriter.write(average+" ");			
		}
	}
	public static int totalgrades(int []pergrade){			//计算各科总成绩的方法
		int total=0;
		for(int grade:pergrade){	//计算总和
			total+=grade;
		}
		return total;
	}
	public static void sorttotalgrades(int []setoftotalgrades){		//对总成绩排序的方法
		Sort grade=new Sort();			//创建Sort类的对象grade
		grade.sort(setoftotalgrades, 0, setoftotalgrades.length-1);		//调用Sort类中的sort函数
	}
	public static void sortgradeslist(int [][]b,int[]a){		//按照总成绩对成绩列表排序的方法
		for(int i=0;i<Gradeslist.size()-1;i++){
			for(int j=i;j<Gradeslist.size()-1;j++){
				b=grades();				//更新b数组
				if(totalgrades(b[j])==a[i]){			//开始排序
					String[] d=(String[])(Gradeslist.get(i+1));
					Gradeslist.set(i+1, Gradeslist.get(j+1));
					Gradeslist.set(j+1, d);
				break;
				}
			}
		}
	}
	public static void outputTxtFile(String outputpath) throws IOException{			//将结果输出到txt文件的方法
		File newdirpath = new File(outputpath);		//创建一个新File类的对象file
		
		if(!newdirpath.exists()){		//判断是否已经存在，不存在即创建
		newdirpath.mkdirs();
		}else{			//存在便删除，然后再创建
		newdirpath.delete();
		newdirpath.mkdirs();
		System.out.println("File Create Success.");
		}
		String GradeSortOutput="GradeSortOutput";
		
		File file = new File(outputpath+GradeSortOutput+".txt");	//创建txt文件并输出结果
		
		if(file.exists()){
		System.out.println("文件已存在，请删除后再试");
		System.exit(0);
		}
		file.createNewFile();
		bufferedWriter = new BufferedWriter(new FileWriter(file,true)); 
		
		printGradesBook();			//调用输出printGradesBook()
	
		bufferedWriter.close();		//结束输出
	}

	public static void main(String[] args) throws IOException {
		String inputpath_1="InputAndOutput\\OriginalRecords.txt";		//OriginalRecords文件位置
		String inputpath_2="InputAndOutput\\NameList.txt";		//readnamelist文件位置
		String outputpath = "InputAndOutput\\"+File.separator;		//要输出的文件的位置
		
		readOriginalRecords(inputpath_1);			//读OriginalRecords
		readnamelist(inputpath_2,getlistnumbers(inputpath_2));		//读namelist
		
		int [][]grade=grades();
		int []setoftotalgrades=new int[grade.length];
		
		for(int i=0;i<grade.length;i++){				//计算每人总成绩并放在数组setoftotalgrades中
			setoftotalgrades[i]=totalgrades(grade[i]);		
		}
		
		sorttotalgrades(setoftotalgrades);		//对每人的总成绩排序
		sortgradeslist(grade,setoftotalgrades);		//根据总成绩排序进行成绩单排序
		
		outputTxtFile(outputpath);		//在文件中输出结果
		
		}
	}
