package hashmap;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmails {

	public int numUniqueEmails(String[] emails) {
		// Hash Set to store all the unique emails
		Set<String> uniqueEmails = new HashSet<>();

		for (String email : emails) {
			// split into two parts
			String[] parts = email.split("@");

			// split local by '+'
			String local = parts[0].split("\\+")[0];

			// remove all '.'
			local = local.replace(".", "");

			// add to set
			uniqueEmails.add(local + "@" + parts[1]);
		}

		// return the size of the set
		return uniqueEmails.size();
	}

	// write the driver code for the above function

	/**
	 * Input: emails =
	 * ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
	 * Output: 2 Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually
	 * receive mails.
	 */
	public static void main(String[] args) {
		UniqueEmails ue = new UniqueEmails();
		String[] emails = {"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com",
			"testemail+david@lee.tcode.com"};

		System.out.println(ue.numUniqueEmails(emails));
	}

}
