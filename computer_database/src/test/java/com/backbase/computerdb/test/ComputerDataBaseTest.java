package com.backbase.computerdb.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.backbase.computerdb.pages.AddComputer;
import com.backbase.computerdb.pages.EditComputer;
import com.backbase.computerdb.pages.HomePage;
import com.backbase.computerdb.testbase.TestBase;
public class ComputerDataBaseTest extends TestBase {
 
	HomePage homepage;
	AddComputer addComp;
	EditComputer editComp;
	public String homePageComputerCount;
	@BeforeClass
	public void intialization() {
		//set up the initial set up 
		intialize();
		homepage  = new HomePage();
		addComp = new AddComputer();
		editComp = new EditComputer();
	}
	//search the computer 
	public void search(String updatedComputerName ) {
		homepage.filterByName(updatedComputerName);
		homepage.clickFilteredElement(updatedComputerName);
	}
	
	@Test(description = "Verify to be on home page", priority = 1)
	public void validateHomePage() {
		
		//get the title of home page
		String title = homepage.validatePageTitle();
		Assert.assertEquals(title, "Computers database");
			
	}
	
	// Test cases are based of CRUD operations


	@Parameters({"computerName", "introducedDate", "discontinuedDate","company"})
	@Test (description = "Verify to add a computer in computer database", priority = 2)
	public void createComputer(String computerName,String introducedDate,String discontinuedDate,String company) {
	
		//before adding the computer get the total available count of computer
		String availableComputers = homepage.getTotalComputers();
		homepage.clickAddComputer();
		//enter the input details of computer
		addComp.enterComputerDetails(computerName, introducedDate, discontinuedDate, company);
		//save the computer
		addComp.createComputer();
		//get the total count of computer after adding the new computer
		String updatedComputers = homepage.getTotalComputers();
		//verify the count
		boolean result = addComp.verifyComputer(availableComputers,updatedComputers);
		Assert.assertTrue(result);
		Assert.assertEquals("Done! Computer " + computerName + " has been created", homepage.getCreateCompMsg());
		
	}
	
	
@Parameters({"computerName", "updatedComputerName", "updatedIntroducedDate","updatedDiscontinuedDate","updatedCompany"})
@Test (description = "Verify to edit a computer in computer database", priority = 3)
public void editComputer(String computerName,String updatedComputerName,String updatedIntroducedDate,String updatedDiscontinuedDate,String updatedCompany) {
	// Search computer 
	search(computerName);
	String title = editComp.getEditComputerPageTitle();
	//homePageComputerCount= homepage.getTotalComputers();
	Assert.assertEquals(title, "Edit computer");
	// Edit filtered computer and save
	editComp.editDetails(updatedComputerName,updatedIntroducedDate, updatedDiscontinuedDate, updatedCompany);
	editComp.saveComputer();
	Assert.assertEquals("Done! Computer " + updatedComputerName + " has been updated", homepage.getEditComputerMsg());
		
		
	}
@Parameters({"updatedComputerName"})
@Test (description = "Verify to get computers result from computer", priority = 4)
public void findByName(String updatedComputerName) {
	homePageComputerCount= homepage.getTotalComputers();
	search(updatedComputerName);
	Assert.assertEquals(updatedComputerName, editComp.getComputerName());
		
	}
@Parameters({"updatedComputerName"})
@Test (description = "Verify to delete the computer", priority = 5)
public void deleteTest(String updatedComputerName) {
	
	//redirect to the homepage
	homepage.redirectToHomePage();
	// Get total computers count before deleting
	String availableComputers =homepage.getTotalComputers();
	//Search computer
	search(updatedComputerName);
	editComp.deleteComputer();
	
	// Get total computers after deleting
	String updatedComputers = homepage.getTotalComputers();
	Assert.assertNotEquals(availableComputers, updatedComputers);
	Assert.assertEquals("Done! Computer has been deleted", homepage.getDeleteComputerMsg());
}

}
