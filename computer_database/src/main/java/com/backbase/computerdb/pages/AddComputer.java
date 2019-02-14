package com.backbase.computerdb.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.backbase.computerdb.testbase.TestBase;

public class AddComputer extends TestBase {
	// Identifying web elements

		@FindBy(id = "name")
		 WebElement computerName;

		@FindBy(id = "introduced")
		private WebElement introducedDate;

		@FindBy(id = "discontinued")
		private WebElement discontinuedDate;

		@FindBy(id = "company")
		private WebElement companyDropdown;

		@FindBy(css = "input.btn.primary")
		private WebElement createComputer;

		@FindBy(css = "input.btn")
		private WebElement cancel;

		public AddComputer() {
			PageFactory.initElements(driver, this);
			
		}

		/**

		 * Enter the computer details

		 * @param compName	computer name

		 * @param intrDate	introduced date

		 * @param disDate	discontinued date

		 * @param comp		company

		 */

		public void enterComputerDetails(String compName, String intrDate, String disDate, String comp) {

			computerName.sendKeys(compName);
			introducedDate.sendKeys(intrDate);
			discontinuedDate.sendKeys(disDate);
			companyDropdown.sendKeys(comp);

		}
		
		/**
		 * Create computer
		 */

		public void createComputer() {

			createComputer.click();

		}
		/**
		 * Verify that computer is added successfully
		 * @param updateComputers
		 * @param availableComputers
		 * @return boolean
		 */
		public boolean verifyComputer(String updateComputers ,String availableComputers) {
		
			// Get total computers before adding new computer
			System.out.println(updateComputers +"  "+availableComputers);
			return (updateComputers.equals(availableComputers))? false :true;
		
		}

		/**
		 * Cancel computer creation
		 */

		public void cancelCreation() {

			cancel.click();

		}

}
