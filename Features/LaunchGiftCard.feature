
Feature: Open gift card page

@Sanity
	Scenario: Successful redirect to gift card page
		Given user launch browser
		And opens the url "https://www.makemytrip.com/"
		When user clicks on gift card option
		Then redirects to the gift card page