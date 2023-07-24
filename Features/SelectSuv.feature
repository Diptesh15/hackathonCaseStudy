Feature: Select SUV

@Sanity
	Scenario: Successfully display only SUVs from the result
		Given user launch browser
		And opens the url "https://www.makemytrip.com/"
		When user clicks on cab weblink
		And user enter data for cab search
		When user sort the results by selecting suv
		Then user will get suv available as per criteria