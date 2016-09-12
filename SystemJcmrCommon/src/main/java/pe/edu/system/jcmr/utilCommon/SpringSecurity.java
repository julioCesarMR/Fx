package pe.edu.system.jcmr.utilCommon;

public class SpringSecurity {
//	  @FXML public TextField      txtPassword;
//	    @FXML public TextField      txtUsuario;
////	    @FXML private ContextMenu   idMenuMsj;
////	    @FXML private Tooltip       toolUsuario;
////	    @FXML private Label         lblMensaje;
//
//
////	    ValidationSupport validationSupport = new ValidationSupport();
//	//
////	    private static LoguinFXMLController instance;
//
//
//		EmployedService employedService =(EmployedService) getBean("employedService");
//		RolService rolService =(RolService) getBean("rolService");
//		@Autowired
//		private Security security;
//
//
//		@FXML Button btnIngresar;
//	    private final  String LOGUIN_FXML ="/META-INF/negocio/main/fxml/main.fxml";
//		 
//		
//	    
//	    @FXML public void btnIngresar(ActionEvent event) throws IOException {
//	        
//	    	
////	    	for(TbRol rol : rolService.listAll()){
////	    		System.out.println(rol.getDescripcion());
////	    	}
//	//
////	         System.out.println(rolService.listAll());
//	    
////	    	
//		//System.out.println(employedService.listAll().size());
////	    	DownloadFile x = new DownloadFile();
////			x.downloadFile("http://www2.sunat.gob.pe/padron_reducido_ruc.zip","html","foto","C:/Users/cmeza/Desktop");
//	    	
//	    //	MessageBox x = new MessageBox();
//	    //	x.show();
//	   
//	    	      
//	 
//
//
//
//				TbEmpleado employed  = employedService.authenticateUser("12345678", "12345678");
//
//				if (employed != null)
//				{
//
//					aute("1234578", "12345678");
//					
//					
//				//	if (employed.getDni() == Integer.parseInt(txtUsuario.getText()) && employed.getClave().equals(txtPassword.getText()))
//
//			  {
//				  
//				  
//				  
////				  security.authenticate("12345678", "12345678",rolService.listAll());
//				  
////				  Authentication authToken = new UsernamePasswordAuthenticationToken("12345678", "12345678");
////				  
////				  MyAuthenticationProvider x = new MyAuthenticationProvider();
//	//
////				  x.authenticate(authToken);
//	                //  showMain(event);
//
////						messageSuccess("Bienvenido: "+employed.getNombre()+" "+employed.getApellido());
////					    String title = "Bienvenido";
////				        String message = "Bienvenido "+employed.getNombre()+" "+employed.getApellido()+" "+employed.getTbRol().getDescripcion();
////				        Notification notification = Notifications.SUCCESS;
//	//
////				        TrayNotification tray = new TrayNotification();
////				        tray.setTitle(title);
////				        tray.setMessage(message);
////				        tray.setImage(convertByteToImage(employed.getFoto()));
////				        tray.showAndDismiss(Duration.seconds(15));
////				        tray.setRectangleFill(Paint.valueOf("#8ba7fa"));
////				        tray.setNotification(notification);
////				        tray.showAndWait();
////				
//						SessionJCMR.getInstance().addContextObject("Usuario",employed);
//				    	closeWindow(btnIngresar);
//					    openWindow(LOGUIN_FXML);
//				        
//					}
//				}
//
//	}
//	    private Stage dialogStage = null;
//	    public void showView(Stage mainStage) {
//	  		if (dialogStage == null) {
//	  			AnchorPane root = (AnchorPane) Index.loader.load("/LoguinFXML.fxml");
//	  			dialogStage = new Stage();
//	  			dialogStage.initOwner(mainStage);
//	  			dialogStage.initModality(Modality.WINDOW_MODAL);
//	  			Scene scene = new Scene(root);
//	  			dialogStage.setScene(scene);
//	  			dialogStage.setResizable(false);
//	  			dialogStage.initStyle(StageStyle.UNDECORATED);
//	  		}
//	  		dialogStage.show();
//	  	}
//	    
//	 	public EmployedService getEmployedService() {
//	  		return employedService;
//	  	}
//
//	  	public void setEmployedService(EmployedService employedService) {
//	  		this.employedService = employedService;
//	  	}
//	  	public Security getSecurity() {
//	  		return security;
//	  	}
//	  	public void setSecurity(Security security) {
//	  		this.security = security;
//	  	}
//	    
//	    private static AuthenticationManager am = new SampleAuthenticationManager();
//
//	    public void aute(String use , String pas) {
//
//	        try {
//	          Authentication request = new UsernamePasswordAuthenticationToken(use, pas);
//	          Authentication result = am.authenticate(request);
//	          SecurityContextHolder.getContext().setAuthentication(result);
//	    
//	        } catch(AuthenticationException e) {
//	          System.out.println("Authentication failed: " + e.getMessage());
//	        }
//	      }
//	 //     System.out.println("Successfully authenticated. Security context contains: " +SecurityContextHolder.getContext().getAuthentication());
//	    
//	  }
//
//	  class SampleAuthenticationManager implements AuthenticationManager {
//	    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
//
//	    static {
//	      AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
//	    }
//
//	    public Authentication authenticate(Authentication auth) throws AuthenticationException {
//	      if (auth.getName().equals(auth.getName()) && auth.getCredentials().equals(auth.getCredentials())) {
//	      
//	    	  return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), AUTHORITIES);
//	       }
//	      
//	      throw new BadCredentialsException("Bad Credentials");
//	    }
//	  }

}
