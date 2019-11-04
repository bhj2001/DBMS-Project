package com.example.dbms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.dbms.dao.employeeDAO;
import com.example.dbms.dao.productDAO;
import com.example.dbms.dao.retailerDAO;
import com.example.dbms.model.Attendance;
import com.example.dbms.model.employee;
import com.example.dbms.model.feedback;
import com.example.dbms.model.inventory;
import com.example.dbms.model.manages;
import com.example.dbms.model.orders;
import com.example.dbms.model.product;
import com.example.dbms.model.retailer;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
// import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyApplication {

    @Autowired
    private retailerDAO retailerdao;

    @Autowired
	private employeeDAO employeedao;
	
	@Autowired 
	private productDAO productdao;

	private static Integer cnt=0;

    @RequestMapping("/")
    public String func(Model model,Principal principal){
		String role=new String();
		String uname=new String();
		try {
			uname=principal.getName();
		} catch (Exception e) {
			//TODO: handle exception
		}
		
		
		if(uname!=null) role=retailerdao.getrole(uname);
		else uname=null;
		model.addAttribute("role", role);
		model.addAttribute("cnt", ++cnt);
        return "home";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(Model model) {
		
		retailer r=new retailer();
		model.addAttribute("user", r);

		return "registerClient";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerProcess(@Valid @ModelAttribute("user") retailer r,BindingResult result,Model model) {
		if(result.hasErrors()) {
			System.out.println("This is error");
			return "register";
		}
		else {
			if(!r.getPassword().equals(r.getmPassword())) {
				model.addAttribute("error","passwords dont match");
				return "registerClient";
			}
			List<employee> le=employeedao.empList();
            int fnd=0;
            for(employee ee:le){
                if(ee.getEmpid().equals(r.getRid())) fnd=1;
			}
			List<retailer> lr=retailerdao.retailerList();
			for(retailer rr:lr){
				if(rr.getRid().equals(r.getRid())) fnd=1;
			}
            if(fnd==1 ){
                model.addAttribute("error","user name exists");
				return "registerClient";
			}
			if(retailerdao.doesUsernameExists(r.getRid())){
				model.addAttribute("error","user name exists");
				return "registerClient";
			}
			retailerdao.save(r);
			return "redirect:/login";
		}
	}

    @RequestMapping("/admin")
    public String admin(Model model,Principal principal){
        model.addAttribute("user", principal.getName());
        return "admin";
    }

    @RequestMapping(value = "/admin/markAttendance", method = RequestMethod.GET)
	public ModelAndView markAttendance() {
		List<String> employeesnamelist = employeedao.getEmployeesNameList();
		System.out.println(Arrays.toString(employeesnamelist.toArray()));
		ModelAndView model = new ModelAndView("markAttendance");
		model.addObject("attendance", new Attendance()); 
		model.addObject("employeesnamelist", employeesnamelist);
		return model;
	}
	
	@RequestMapping(value = "/admin/attendanceMarked", method = RequestMethod.POST)
	public ModelAndView attendanceMarked(@ModelAttribute Attendance attendance) throws ParseException {
		java.util.Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		attendance.setDate(sqlDate);
		System.out.println(sqlDate);
		employeedao.finallyMarkAttendance(attendance);
		return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/admin/viewAllAttendance", method = RequestMethod.GET)
	public String viewAllAttendance(Model model, Principal principal) {
		List<List<String>> allattendancelist = employeedao.getAllAttendanceList();
		System.out.println(Arrays.toString(allattendancelist.toArray()));
		model.addAttribute("allattendancelist", allattendancelist);
		model.addAttribute("attendance", new Attendance());
		return "viewAllAttendance";
    }
    
    @RequestMapping(value = "/admin/viewAllAttendance", method = RequestMethod.POST)
	public ModelAndView feedbackSent(@Valid @ModelAttribute("attendance") Attendance attendance,BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("viewAllAttendance");
		}
		else {
			String username = attendance.getEmpid();
			if(username.equals("")) {
				List<List<String>> allattendancelist = employeedao.getAllAttendanceList();
				ModelAndView model = new ModelAndView("viewAllAttendance");
				model.addObject("allattendancelist", allattendancelist);
				return model;
			}
            List<List<String>> allattendancelist = employeedao.getUserAttendanceList(username);
			ModelAndView model = new ModelAndView("viewAllAttendance");
			model.addObject("allattendancelist", allattendancelist);
			return model;
		}
    }
    
    @RequestMapping(value = "/admin/viewEmployees", method = RequestMethod.GET)
	public String viewEmployees(Model model, Principal principal) {
		List<List<String>> employeelist = employeedao.getEmployeesList();
		System.out.println(Arrays.toString(employeelist.toArray()));
		model.addAttribute("employeelist", employeelist);
		return "viewEmployees";
    }
    
    @RequestMapping(value="/admin/registerEmployee",method=RequestMethod.GET)
	public String registerEmployee(Model model) {
		
		employee e=new employee();
		model.addAttribute("e", e);

		return "registerEmployee";
	}
	
	@RequestMapping(value="/admin/registerEmployee",method=RequestMethod.POST)
	public String registerEmployeeProcess(@Valid @ModelAttribute("e") employee e,BindingResult result,Model model) {
        
        if(result.hasErrors()) {
			System.out.println("Error2");
			return "registerEmployee";
		}
		else {
			System.out.println("Error");
			if(!e.getPassword().equals(e.getmPassword())) {
				model.addAttribute("error","passwords dont match");
				return "registerEmployee";
            }
            if(retailerdao.doesUsernameExists(e.getEmpid())){
                model.addAttribute("error","user name exists");
				return "registerEmployee";
            }
            System.out.println(e.toString());
			employeedao.save(e);
			return "redirect:/admin";
		}
	}

	@RequestMapping(value = "/admin/viewClients", method = RequestMethod.GET)
	public String viewClients(Model model, Principal principal) {
		List<List<String>> clientslist = retailerdao.getRetailerList();
		System.out.println(Arrays.toString(clientslist.toArray()));
		model.addAttribute("clientslist", clientslist);
		return "viewClients";
	}

	@RequestMapping(value = "/admin/viewFeedbacks", method = RequestMethod.GET)
	public String viewFeedbacks(Model model, Principal principal) {
		List<List<String>> submittedfeebacks = retailerdao.getFeedbacks();
		System.out.println(Arrays.toString(submittedfeebacks.toArray()));
		model.addAttribute("submittedfeebacks", submittedfeebacks);
		return "viewFeedbacks";
	}

	@RequestMapping(value = "/admin/viewAllOrders", method = RequestMethod.GET)
	public String viewAllOrders(Model model, Principal principal) {
		List<List<String>> allorderslist = productdao.getAllOrdersList();
		System.out.println(Arrays.toString(allorderslist.toArray()));
		model.addAttribute("allorderslist", allorderslist);
		return "viewAllOrders";
	}

	@RequestMapping(value = "/admin/addProduct", method = RequestMethod.GET)
	public String addProductMedicine(Model model, Principal principal) {
		model.addAttribute("product", new product());
		return "addProductMedicine";
	}
	
	@RequestMapping(value = "/admin/productAdded", method = RequestMethod.POST)
	public ModelAndView rawMaterialAdded(@ModelAttribute product p) {
		if(productdao.existsProdWithId(p.getProductid())){
			ModelAndView model=new ModelAndView("addProductMedicine");
			model.addObject("product", p);
			model.addObject("error", "Product Id already exists");
			return model;
		}
		String[] comp=p.getCompanies().split(",");
		for(String s:comp){
			if(!productdao.existsCompWithId(s)){
				ModelAndView model=new ModelAndView("addProductMedicine");
				model.addObject("product", p);
				model.addObject("error", "Company Id "+s+" does not exists");
				return model;
			}
		}
		String[] chems=p.getChemicals().split(",");
		for(String s:chems){
			if(!productdao.existsChemWithId(s)){
				ModelAndView model=new ModelAndView("addProductMedicine");
				model.addObject("product", p);
				model.addObject("error", "Chemical Id "+s+" does not exists");
				return model;
			}
		}
		productdao.addProduct(p);
		for(String s:comp){
			productdao.addProdManufByComp(p.getProductid(),s);
		}
		for(String s:chems){
			productdao.addProdContainsChem(p.getProductid(),s);
		}
		return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/admin/markAsComplete", method = RequestMethod.GET)
	public String markAsComplete(HttpServletRequest request) {
		String orderid = request.getParameter("id");
		String username = request.getParameter("user");
		// String type = request.getParameter("type");
		// type = type.replaceAll("'","");
		productdao.updateOrderStatus(orderid, username, "Done");
		return "redirect:/admin/viewAllOrders";
	}

	@RequestMapping(value = "/admin/viewInvoices", method = RequestMethod.GET)
	public String viewInvoices(Model model, Principal principal) {
		List<List<String>> invoicelist = productdao.getInvoiceList();
		System.out.println(Arrays.toString(invoicelist.toArray()));
		model.addAttribute("invoicelist", invoicelist);
		return "viewInvoices";
	}

	@RequestMapping(value = "admin/download/pdf/{fileName}", method = RequestMethod.GET)
	public void downloadFileadmin(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) {
	    try {
			// /home/bhagya/Desktop/dbms/src/main/webapp/WEB-INF/downloads/pdf
	        String filePathToBeServed = "./src/main/webapp/WEB-INF/downloads/pdf/"+fileName+".pdf";
	        File fileToDownload = new File(filePathToBeServed);
	        InputStream inputStream = new FileInputStream(fileToDownload);
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename="+fileName+".pdf"); 
	        IOUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        inputStream.close();
	    } catch (Exception e){
	        e.printStackTrace();
	    }
	}

	@RequestMapping(value = "/admin/alterPricings", method = RequestMethod.GET)
	public String alterPricings(Model model, Principal principal) {
		List<String> productslist = productdao.getProducts();
		System.out.println(Arrays.toString(productslist.toArray()));
		model.addAttribute("productslist", productslist);
		model.addAttribute("product", new product());
		return "alterPricings";
	}

	@RequestMapping(value="admin/pricesAltered", method = RequestMethod.POST)
	public ModelAndView pricesAltered(@ModelAttribute product p) {
		productdao.updatePrices(p);
		return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/admin/viewProducts", method = RequestMethod.GET)
	public String viewAllProducts(Model model, Principal principal) {
		List<List<String>> productlist = productdao.getAllProducts();
		System.out.println(Arrays.toString(productlist.toArray()));
		model.addAttribute("productlist", productlist);
		return "viewProducts";
	}

	@RequestMapping(value = "/admin/addManager", method = RequestMethod.GET)
	public String addManager(Model model, Principal principal) {
		model.addAttribute("manages", new manages());
		return "addManager";
	}
	
	@RequestMapping(value = "/admin/managerAdded", method = RequestMethod.POST)
	public ModelAndView managerAdded(@ModelAttribute manages m) {
		try {
			employeedao.addManager(m);
		} catch (Exception e) {
			System.out.println("error occured while inserting manager");
		}
		return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = "/admin/viewCompanyList", method = RequestMethod.GET)
	public String viewCompanyListadmin(Model model, Principal principal) {
		List<List<String>> complist = productdao.getCompList();
		model.addAttribute("complist", complist);
		return "viewCompanyList";
	}

	@RequestMapping(value = "/admin/viewChemicalList", method = RequestMethod.GET)
	public String viewChemicalListadmin(Model model, Principal principal) {
		List<List<String>> chemlist = productdao.getChemList();
		model.addAttribute("chemlist", chemlist);
		return "viewChemicalList";
	}

	@RequestMapping(value = "/admin/viewSpecificOrder", method = RequestMethod.GET)
	public ModelAndView viewSpecificOrder(HttpServletRequest request) {
		String clientusername = request.getParameter("username");
		List<List<String>> specificOrdersList = productdao.getSpecificOrdersList(clientusername);
		System.out.println(Arrays.toString(specificOrdersList.toArray()));
		ModelAndView model = new ModelAndView("viewSpecificOrder");
		model.addObject("specificOrdersList", specificOrdersList);
		model.addObject("clientusername", clientusername);
		return model;
	}

	@RequestMapping(value = "/admin/inventory", method = RequestMethod.GET)
	public String inventory(Model model, Principal principal) {
		List<String> prodlist = productdao.getProdListInventory();
		model.addAttribute("prodlist", prodlist);
		inventory inv=productdao.getInventory();
		model.addAttribute("inventory", inv);
		return "inventory";
	}

	@RequestMapping(value = "/admin/inventoryAltered", method = RequestMethod.POST)
	public String inventoryAltered(Model model, Principal principal,@ModelAttribute("inventory") inventory inv) {
		productdao.alterInventory( inv);
		return "redirect:/admin";
	}
    
    @RequestMapping("/user")
    public ModelAndView user(){
        ModelAndView model=new ModelAndView("displaystuff");
        model.setViewName("displaystuff");
        model.addObject("text", "hello user");
        return model;
    }

    @RequestMapping("/employee")
    public String employee(Model model,Principal principal){
        String loggedInUserName = principal.getName();
		model.addAttribute("employee", loggedInUserName);
		model.addAttribute("name", "Spring Security USER Custom Login Demo");
		model.addAttribute("description", "Protected page for user !");
		return "employee";
    }

    @RequestMapping(value="/employee/editProfile", method = RequestMethod.GET)
	public ModelAndView editEmployeeProfile(Principal principal) {
        String username = principal.getName();
        employee e=employeedao.getEmployee(username);
        System.out.println(e.getEmpid());
		ModelAndView model = new ModelAndView("employeeEdit");
		model.addObject("employee", e);
		return model;
    }

    @RequestMapping(value = "/employee/viewAttendance", method = RequestMethod.GET)
	public ModelAndView viewAllAttendance(Principal principal) {
        String empid = principal.getName();
        System.out.println(empid);
		List<List<String>> clientattendancelist = employeedao.getClientAttendanceList(empid);
		System.out.println(Arrays.toString(clientattendancelist.toArray()));
		ModelAndView model = new ModelAndView("viewAttendance");
		model.addObject("clientattendancelist", clientattendancelist);
		return model;
	}

	@RequestMapping(value = "/employee/viewManagers", method = RequestMethod.GET)
	public ModelAndView viewManagers(Principal principal) {
        String empid = principal.getName();
        System.out.println(empid);
		List<String> managers = employeedao.getManagers(empid);
		ModelAndView model = new ModelAndView("viewManagers");
		model.addObject("managers", managers);
		return model;
	}

	@RequestMapping(value = "/employee/viewSubordinates", method = RequestMethod.GET)
	public ModelAndView viewSubordinates(Principal principal) {
        String empid = principal.getName();
		List<String> subordinates = employeedao.getSubordinates(empid);
		ModelAndView model = new ModelAndView("viewSubordinates");
		model.addObject("subordinates", subordinates);
		return model;
	}
    
    @RequestMapping(value = "/employee/saveEmployeeProfile", method = RequestMethod.POST)
	public ModelAndView saveEmployeeProfile(@ModelAttribute employee e,Principal principal) {
        e.setEmpid(principal.getName());
        System.out.println(e.getAddress());
        System.out.println(e.getEmpid());
		employeedao.editEmployeeProfile(e);
	    return new ModelAndView("redirect:/employee");
    }
    
    @RequestMapping("/client")
	public String user(Model model, Principal principal) {
		String loggedInUserName = principal.getName();
		model.addAttribute("client", loggedInUserName);
		model.addAttribute("name", "Spring Security USER Custom Login Demo");
		model.addAttribute("description", "Protected page for user !");
		return "client";
	}   

	@RequestMapping(value="/client/editProfile", method = RequestMethod.GET)
	public ModelAndView editClientProfile(Principal principal) {
		String rid = principal.getName();
		retailer r=retailerdao.getRetailer(rid);
		ModelAndView model = new ModelAndView("clientEdit");
		model.addObject("client", r);
		return model;
	}
	
	@RequestMapping(value = "/client/saveClientProfile", method = RequestMethod.POST)
	public ModelAndView saveClientProfile(@ModelAttribute retailer r,Principal principal) {
		r.setRid(principal.getName());
		retailerdao.editRetailer(r);
	    return new ModelAndView("redirect:/client");
	}

	@RequestMapping(value="/client/submitFeedback")
	public String enterFeedback(Model model, Principal principal) {
		String clientusername = principal.getName();
		feedback f=new feedback();
		f.setRid(clientusername);
		model.addAttribute("client", clientusername);
        model.addAttribute("feedback", f);
		return "submitFeedback";
	}
	
	@RequestMapping(value="/client/feedbackSent",method=RequestMethod.POST)
	public String feedbackSent(@Valid @ModelAttribute("feedback") feedback f,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "submitFeedback";
		}
		else {
			java.util.Date date = new Date();
	        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			f.setDate(sqlDate);
			retailerdao.saveFeedback(f);
			return  "redirect:/client";
		}
	}

	@RequestMapping(value = "/client/order", method = RequestMethod.GET)
	public String placeOrder(Model model,Principal principal) {
		String client = principal.getName();
		model.addAttribute("client", client);
		model.addAttribute("order", new orders()); 
		List<List<String>> productsAndPriceList = productdao.getProductsAndPrice();
		model.addAttribute("productsAndPriceList", productsAndPriceList);
		return "placeOrder";
	}

	public void createPdf(String text, String dest)
		throws DocumentException, IOException, com.itextpdf.text.DocumentException {
	        Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream(dest));
	        document.open();
	        BufferedReader br = new BufferedReader(new FileReader(text));
	        String line;
	        Paragraph p;
	        BaseFont courier = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1252, BaseFont.EMBEDDED);
//	        Font normal = new Font(FontFamily.TIMES_ROMAN, 12);
	        Font myfont = new Font(courier);
	        Font bold = new Font(courier, 12, Font.BOLD);
	        boolean title = true;
	        while ((line = br.readLine()) != null) {
	            p = new Paragraph(line, title ? bold : myfont);
	            // p.setAlignment(Element.ALIGN_JUSTIFIED);
	            title = line.isEmpty();
	            if(!title)
	            	p.add(Chunk.NEWLINE); 
	            document.add(p);
	        }
	        document.close();
	    }

	@RequestMapping(value = "/client/placeOrderByClient", method = RequestMethod.POST)
	public ModelAndView placeOrderByClient(@ModelAttribute orders order,Principal principal) throws IOException, DocumentException, ParseException, com.itextpdf.text.DocumentException {
		String part1 = UUID.randomUUID().toString();
		long unixTime = System.currentTimeMillis() / 1000L;
		String part2 = String.valueOf(unixTime);
		String id = part1 + part2;
		System.out.println(id);
		order.setOrderid(id);
		order.setStatus("not ready");
		// to check for empty orders
		Integer sm=0;
		HashMap<String, Integer> ma = order.getOrderquantity();
		Set<String> keys = ma.keySet();
        Iterator<String> keysIterator = keys.iterator();
		while(keysIterator.hasNext()) {
			String key = keysIterator.next();
			sm+=ma.get(key);
		}
		System.out.println(sm);
		if(sm==0){
			String client = principal.getName();
			ModelAndView model1 = new ModelAndView("placeOrder");
			model1.addObject("client", client);
			model1.addObject("order",order); 
			List<List<String>> productsAndPriceList = productdao.getProductsAndPrice();
			model1.addObject("productsAndPriceList", productsAndPriceList);
			model1.addObject("error", "there needs to be at least one item in the cart to place order");
			return model1;
		}
		productdao.finalPlaceOrder(order);
		List<List<String>> billlist = productdao.getBillPrice(id);
		System.out.println(billlist.toString());
		System.out.println(Arrays.toString(billlist.toArray()));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		LocalDateTime now = LocalDateTime.now();  
//		System.out.println(dtf.format(now));  
		
		File f = new File("."); // current directory

		File[] files = f.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.print("directory:");
			} else {
				System.out.print("     file:");
			}
			System.out.println(file.getCanonicalPath());
		}
		// /home/bhagya/Desktop/dbms/src/main/webapp
		File files2 = new File("./src/main/webapp/WEB-INF/downloads/txt");
		if (!files2.exists()) {
            if (files2.mkdirs()) {
                System.out.println("Multiple directories are created!");
            } else {
                System.out.println("Failed to create multiple directories!");
            }
        }
		else {
			System.out.println("exists");
		}
		
		File files3 = new File("./src/main/webapp/WEB-INF/downloads/pdf");
		if (!files3.exists()) {
            if (files2.mkdirs()) {
                System.out.println("Multiple directories are created!");
            } else {
                System.out.println("Failed to create multiple directories!");
            }
        }
		else {
			System.out.println("exists");
		}
			
		PrintWriter writer = new PrintWriter("./src/main/webapp/WEB-INF/downloads/txt/" + id+".txt", "UTF-8");
		writer.println("Sunil Wholesale Pharmacy");
		writer.println("------------------");
		writer.println("\n");
		writer.println("Invoice for order => " + id);
		writer.println();
		writer.println("Date =>  " + dtf.format(now).toString());
		writer.println();
		Integer sum = 0;
		String clientusername = null;
		if(billlist.size()>0) {
			List<String> temp = billlist.get(0);
			writer.println("Client name => " + temp.get(0));
			clientusername = temp.get(3);
		}
		writer.println("\r\nOrder Summary\r\n");
		writer.println("-------------");
		for(int i=0; i<billlist.size(); i++) {
			List<String> temp = billlist.get(i);
			if(!temp.get(2).equals("0")) {
				writer.println();
				writer.println(temp.get(1) + "    " + temp.get(2));
				writer.println();
				sum += Integer.parseInt(temp.get(2));
			}
		}
		writer.println("------------------------");
		writer.println();
		writer.println("Total                " + Integer.toString(sum));
		writer.close();		
		
		String text = "./src/main/webapp/WEB-INF/downloads/txt/"+id+".txt";
		String dest = "./src/main/webapp/WEB-INF/downloads/pdf/"+id+".pdf";
		System.out.println("Done txt!");
		File file = new File(dest);
        file.getParentFile().mkdirs();
    	createPdf(text, dest);
    	
		System.out.println("Done pdf!");
		ModelAndView model = new ModelAndView("orderPlaced");
		model.addObject("id", id);
		return model;
	}

	@RequestMapping(value = "client/download/pdf/{fileName}", method = RequestMethod.GET)
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) {
	    try {
			// /home/bhagya/Desktop/dbms/src/main/webapp/WEB-INF/downloads/pdf
	        String filePathToBeServed = "./src/main/webapp/WEB-INF/downloads/pdf/"+fileName+".pdf";
	        File fileToDownload = new File(filePathToBeServed);
	        InputStream inputStream = new FileInputStream(fileToDownload);
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename="+fileName+".pdf"); 
	        IOUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        inputStream.close();
	    } catch (Exception e){
	        e.printStackTrace();
	    }
	}

	@RequestMapping(value = "/client/viewOrders", method = RequestMethod.GET)
	public String viewOrders(Model model, Principal principal) {
		String cname = principal.getName();
		List<List<String>> orderdetails = productdao.getOrdersForClient(cname);
		model.addAttribute("client", cname);
		model.addAttribute("orderdetails", orderdetails);
		return "viewOrders";
	}

	@RequestMapping(value = "/client/viewClientInvoices", method = RequestMethod.GET)
	public String viewClientInvoices(Model model, Principal principal) {
		String username = principal.getName();
		List<List<String>> specificinvoicelist = productdao.getSpecificInvoiceList(username);
		System.out.println(Arrays.toString(specificinvoicelist.toArray()));
		model.addAttribute("specificinvoicelist", specificinvoicelist);
		return "viewClientInvoices";
	}

	@RequestMapping(value = "/client/viewProducts", method = RequestMethod.GET)
	public String viewAllProductsClient(Model model, Principal principal) {
		List<List<String>> productlist = productdao.getAllProducts();
		System.out.println(Arrays.toString(productlist.toArray()));
		model.addAttribute("productlist", productlist);
		return "viewProducts";
	}

	@RequestMapping(value = "/client/viewCompanyList", method = RequestMethod.GET)
	public String viewCompanyList(Model model, Principal principal) {
		List<List<String>> complist = productdao.getCompList();
		model.addAttribute("complist", complist);
		return "viewCompanyList";
	}

	@RequestMapping(value = "/client/viewChemicalList", method = RequestMethod.GET)
	public String viewChemicalList(Model model, Principal principal) {
		List<List<String>> chemlist = productdao.getChemList();
		model.addAttribute("chemlist", chemlist);
		return "viewChemicalList";
	}
	
}