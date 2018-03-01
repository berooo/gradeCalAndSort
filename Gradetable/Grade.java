import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Sort{						//�鲢����ʱ�临�Ӷ�O(N*logN)
	public int[]sort(int []numbers,int low,int high){
	
		int  mid=(low+high)/2;	//�м�ֵ
		
		if(low<high){
			
		sort(numbers,low,mid);	//�����
		
		sort(numbers,mid+1,high);	//���ұ�
		
		merge(numbers,low,mid,high);	//�鲢����
		}
		return numbers;
	}
	public void merge(int []nums,int low,int mid,int high){
		
		int []temp=new int[high-low+1];		//��ʱ���飬������ֵ
		
		int i=low;		//��ָ��
		int j=mid+1;	//��ָ��
		int k=0;	
		
		while(i<=mid&&j<=high){
									//�Ƚ��ϴ�����Ƶ���������
			if(nums[i]>nums[j]){
				temp[k++]=nums[i++];
			}
			else{
				temp[k++]=nums[j++];
			}
		}
		while(j<=high){			//���ұ�ʣ������Ƶ�������
			temp[k++]=nums[j++];
		}
		
		while(i<=mid){			//�����ʣ������Ƶ�������
			temp[k++]=nums[i++];
		}
		
		for(int m=0;m<temp.length;m++){		//���������е�������nums����
			nums[m+low]=temp[m];
		}
	}
}
public class Grade {
	public static ArrayList Gradeslist=new ArrayList();		//����һ��ArrayList��������̬�������
	public static BufferedWriter bufferedWriter;		 //�ı������
	public static BufferedReader bufferedReader;		 //�ı�������
	public static InputStreamReader read;				//�ַ�������
	public static void reading(String filepath){
		try{
		String ecoding="GBK";			
		File file=new File(filepath);			//����һ����File��Ķ���file
		if(file.isFile()&&file.exists()){			//�ж��ļ��Ƿ�����Ƿ��Ǳ�׼�ļ�
			read=new InputStreamReader(new FileInputStream(file),ecoding);	//��file�����ڴ沢���
			bufferedReader=new BufferedReader(read);}		//ͨ��bufferedReader����ת����I/O����ʶ�������
		else{
			System.out.println("�Ҳ���ָ�����ļ���");
		}
	}
		catch(Exception e){					//�����쳣
			System.out.println("��ȡ�ļ����ݳ���");
			e.printStackTrace();
		}
	}
	public static void readOriginalRecords(String filepath){		//���ɼ�����ʼ���ݵķ���
		try{
				reading(filepath);		//������
				
				String [][]messagerecord=new String[6][7];		//����messagerecord��ά���飨��֪�ɼ��������ݴ�С��
				
				for(int i=0;i<6;i++){
	
				messagerecord[i]=bufferedReader.readLine().split("  ");		//��ÿ�����ݴ���messagerecord[i]��ȥ
				
				Gradeslist.add(messagerecord[i]);}		//������messagerecord[i]��ӵ�GradeList��
				
				read.close();		//��������
		}
		catch(Exception e){				//�����쳣
			System.out.println("��ȡ�ļ����ݳ���");
			e.printStackTrace();
		}
	}
	public static int getlistnumbers(String filepath) throws IOException{		//��ȡ�ļ����ı������ķ���
		
		reading(filepath);		//������
		
		 String strLine = bufferedReader.readLine();	//strline��ÿ�е��ı�
	        int totalLines = 0;
	        while (strLine != null) {
	            strLine = bufferedReader.readLine();
	            totalLines++;
	        }
		return totalLines;		//��������
	}
	public static void readnamelist(String filepath,int listnumbers){		//������ķ���
		try{
			reading(filepath);		//������
			
			String [][]namelistrecord=new String[listnumbers][2];	//��������������ά����
			
			for(int m=0;m<listnumbers;m++){
				
				namelistrecord[m]=bufferedReader.readLine().split("  ");	//��ÿ�����ݴ���namelistrecord[i]��ȥ
			}
			
			String [][]messagerecord=new String[listnumbers][7];	//�������������������ݵĶ�ά����
			
			for(int k=0;k<listnumbers;k++){			//������
				for(int f=0;f<7;f++){
					if(f<2){
						messagerecord[k][f]=namelistrecord[k][f];
					}else{
						messagerecord[k][f]=String.valueOf(randomnumber());		//�������������
					}
				}
				Gradeslist.add(messagerecord[k]);		//������messagerecord[i]��ӵ�GradeList��
			}
			read.close();			//��������
		}
		catch(Exception e){		//�����쳣
			System.out.println("��ȡ�ļ����ݳ���");
			e.printStackTrace();
		}
	}
	public static void printGradesBook() throws IOException{
		
		int [][]gradebook=grades();		//����grades������ʼ������
		
		for(int j=0;j<Gradeslist.size();j++){				//���GradesBook
			
			String[] d=(String[])(Gradeslist.get(j));	
			
			for(int i=0;i<7;i++){
				bufferedWriter.write(d[i]+" ");	}
			
			if(j==0){
			bufferedWriter.write("�ܳɼ� ");}
			
			if(j>0){
				bufferedWriter.write(String.valueOf(totalgrades(gradebook[j-1])));		//����totalgrades����
			}
			bufferedWriter.newLine();		//����
	}
		bufferedWriter.write("ƽ���ɼ��� ");
		getAverage(gradebook);		//����getAverage����
	}
	public static int randomnumber(){				//�������������
		
		int rnum=(int) (Math.sqrt(169)*(new Random().nextGaussian())+75);		//������̬�ֲ������
		return rnum;
	}
	public static int[][] grades(){				//��ȡ�ɼ����ݷ���
		
		int [][]grade=new int[Gradeslist.size()-1][5];		//����Gradeslist��Ĵ洢�ṹ��������
		
		for(int i=1;i<Gradeslist.size();i++)
			{String[] d=(String[])(Gradeslist.get(i));		//��ȡÿ������
			for(int j=0;j<5;j++){
				grade[i-1][j]=Integer.parseInt(d[j+2]);			//����Ӧ���ַ���ת��Ϊ����
			}
		}
		return grade;
	}
	public static void getAverage(int [][]setofgrades) throws IOException	//��ȡ�ɼ�ƽ��ֵ�ķ���
	{
		int total=0;
		double Average=0;
		
		for(int i=0;i<5;i++){				//�����ܺ�
			for(int j=0;j<setofgrades.length;j++){
				total+=setofgrades[j][i];
			}
			Average=(double)total/setofgrades.length;		//��ƽ��ֵ
			
			String average=String.format("%.2f", Average);		//ת��Ϊ������λС�����ַ�����ʽ
			
			bufferedWriter.write(average+" ");			
		}
	}
	public static int totalgrades(int []pergrade){			//��������ܳɼ��ķ���
		int total=0;
		for(int grade:pergrade){	//�����ܺ�
			total+=grade;
		}
		return total;
	}
	public static void sorttotalgrades(int []setoftotalgrades){		//���ܳɼ�����ķ���
		Sort grade=new Sort();			//����Sort��Ķ���grade
		grade.sort(setoftotalgrades, 0, setoftotalgrades.length-1);		//����Sort���е�sort����
	}
	public static void sortgradeslist(int [][]b,int[]a){		//�����ܳɼ��Գɼ��б�����ķ���
		for(int i=0;i<Gradeslist.size()-1;i++){
			for(int j=i;j<Gradeslist.size()-1;j++){
				b=grades();				//����b����
				if(totalgrades(b[j])==a[i]){			//��ʼ����
					String[] d=(String[])(Gradeslist.get(i+1));
					Gradeslist.set(i+1, Gradeslist.get(j+1));
					Gradeslist.set(j+1, d);
				break;
				}
			}
		}
	}
	public static void outputTxtFile(String outputpath) throws IOException{			//����������txt�ļ��ķ���
		File newdirpath = new File(outputpath);		//����һ����File��Ķ���file
		
		if(!newdirpath.exists()){		//�ж��Ƿ��Ѿ����ڣ������ڼ�����
		newdirpath.mkdirs();
		}else{			//���ڱ�ɾ����Ȼ���ٴ���
		newdirpath.delete();
		newdirpath.mkdirs();
		System.out.println("File Create Success.");
		}
		String GradeSortOutput="GradeSortOutput";
		
		File file = new File(outputpath+GradeSortOutput+".txt");	//����txt�ļ���������
		
		if(file.exists()){
		System.out.println("�ļ��Ѵ��ڣ���ɾ��������");
		System.exit(0);
		}
		file.createNewFile();
		bufferedWriter = new BufferedWriter(new FileWriter(file,true)); 
		
		printGradesBook();			//�������printGradesBook()
	
		bufferedWriter.close();		//�������
	}

	public static void main(String[] args) throws IOException {
		String inputpath_1="InputAndOutput\\OriginalRecords.txt";		//OriginalRecords�ļ�λ��
		String inputpath_2="InputAndOutput\\NameList.txt";		//readnamelist�ļ�λ��
		String outputpath = "InputAndOutput\\"+File.separator;		//Ҫ������ļ���λ��
		
		readOriginalRecords(inputpath_1);			//��OriginalRecords
		readnamelist(inputpath_2,getlistnumbers(inputpath_2));		//��namelist
		
		int [][]grade=grades();
		int []setoftotalgrades=new int[grade.length];
		
		for(int i=0;i<grade.length;i++){				//����ÿ���ܳɼ�����������setoftotalgrades��
			setoftotalgrades[i]=totalgrades(grade[i]);		
		}
		
		sorttotalgrades(setoftotalgrades);		//��ÿ�˵��ܳɼ�����
		sortgradeslist(grade,setoftotalgrades);		//�����ܳɼ�������гɼ�������
		
		outputTxtFile(outputpath);		//���ļ���������
		
		}
	}
