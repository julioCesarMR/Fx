package pe.edu.system.jcmr.gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.edu.system.jcmr.entity.TbEmpleado;
import pe.edu.system.jcmr.entity.TbRol;
import pe.edu.system.jcmr.entity.TbUbigeo;
import pe.edu.system.jcmr.service.EmployedService;
import pe.edu.system.jcmr.util.UtilidadesFx;

public class insert  extends UtilidadesFx{

	public static void main(String[] args) {
		try{
			short x=1;
			int v=1;
			System.out.println(x*v);
			
			ApplicationContext context =  new ClassPathXmlApplicationContext("classpath:/META-INF/spring-context.xml");
	    	 
			EmployedService m = (EmployedService) context.getBean("employedService");
		
			ArrayList<TbEmpleado> em = new ArrayList<>();
			TbEmpleado empleado;
			BufferedReader br;
			String linea,ruc,nombre,estaContri,condicionDomicilio,ubigeo,Tvia,nombreVia,codZona,tipZona,numero,lote,departamento,manzana,kilomento;
			String name = null;
			StringTokenizer st;
			br = new BufferedReader(new FileReader("C:/Users/Julio/Desktop/padron_reducido_ruc.txt"));
			 int n=3631;
			 int z=3;
			while((linea = br.readLine())!=null)
			{
//				Thread.sleep(52);

				st = new StringTokenizer(linea,"|");
				ruc= st.nextToken().trim();
				nombre= st.nextToken().trim();
				estaContri =st.nextToken().trim();
			    condicionDomicilio=st.nextToken().trim();
				ubigeo=st.nextToken().trim();
				Tvia= st.nextToken().trim();
				nombreVia = st.nextToken().trim();
				codZona = st.nextToken().trim();
				tipZona= st.nextToken().trim();
				numero= st.nextToken().trim();
				lote= st.nextToken().trim();
				departamento= st.nextToken().trim();
				manzana = st.nextToken().trim();
				kilomento= st.nextToken().trim();
				
				if (ruc.startsWith("10")) {
					empleado = new TbEmpleado();
					if (!ruc.equals("RUC")) {
						   empleado.setDni(Integer.parseInt(ruc.substring(0, 8)));
					}
					
					if (nombre.split(" ")[2] != null) {
						name = nombre.split(" ")[2];

					}
	
//					 empleado.setEmpleadoId(z++);
					 empleado.setNombre(name);
					 empleado.setApellido(nombre.split(" ")[0]+" "+ nombre.split(" ")[1]);
					 empleado.setDireccion(condicionDomicilio);
					 empleado.setEmail(name+"@hotmail.com");
					 empleado.setCelular(ruc);

					 empleado.setSexo("M");
					 empleado.setTelefono(ruc);
			         empleado.setFechaNac(new Date());
			         n++;
			         if(n>=50){
			        	 n=3631;
			         }
			         TbUbigeo u = new TbUbigeo();
			         u.setUbigeoId(n);
			         
			         empleado.setTbUbigeo(u);
			         TbRol r = new TbRol();
			         r.setIdrol(1);
			         empleado.setTbRol(r);
                     System.out.println("d");
                     
				
					 m.insert(empleado);

				}
	
				
	
			
				
			}
				br.close();
	
			}
			catch(Exception e)
			{
			e.printStackTrace();	
			}
			
				
	}
}
