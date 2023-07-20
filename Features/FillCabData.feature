
Feature: Set data for cab search
@Sanity
	Scenario: After entering the data results are successfully displayed
		Given user launch browser
		And opens the url "https://www.makemytrip.com/"
		When user clicks on cab weblink
		And user enter data for cab search
		Then results are displayed
