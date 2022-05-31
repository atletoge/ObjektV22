package del1;

import java.util.HashMap;

public class VoterCounter {

	// Add any needed fields here
	private String winner;
	private HashMap<String, Integer> oversikt = new HashMap<>();
	public static final String TIE_RESULT = "TIE";

	/**
	 * Register a candidate to the poll
	 * 
	 * @param candidate the new candidate to add
	 */
	public void addCandidate(String candidate) {
		if(!oversikt.containsKey(candidate)) {
			oversikt.put(candidate, 0);
		} else {
			throw new IllegalArgumentException("Kandidaten er allerede registrert");
		}
	}

	/**
	 * Vote on a given candidate
	 * 
	 * @param candidate the candidate to vote on
	 * 
	 * @throws IllegalArgumentException if the candidate is not registered
	 */
	public void castVote(String candidate) {
		if(oversikt.containsKey(candidate)) {
			Integer dummy = oversikt.get(candidate);
			dummy++;
			oversikt.put(candidate, dummy);
		} else {
			throw new IllegalArgumentException("Kandidaten må legges til før du kan stemme på den");
		}
	}

	/**
	 * Prints all the results in an arbitrary order. The results should be on the
	 * format {candidate name}-{number of votes for the candidate} with each
	 * candidate on a new line
	 * 
	 * Example output with two candidates, "Candidate1" with 7 votes, and
	 * "Candidate2" with 6 votes
	 * 
	 * Candidate1-7 
	 * Candidate2-6
	 */
	public String getFormattedResults() {
		StringBuilder formattedResults = new StringBuilder();
		for(String key : oversikt.keySet()) {
			formattedResults.append(key+" - "+oversikt.get(key)+" , \n");
		}
		return formattedResults.toString();
	}

	/**
	 * Returns the number of votes a candidate has received
	 * 
	 * @param candidate the candidate to get number of votes for
	 * @return the number of votes the candidate has received. Returns null if the
	 *         candidate is not registered
	 */
	public Integer getNumberOfVotes(String candidate) {
		if(oversikt.containsKey(candidate)) {
			return oversikt.get(candidate);
		}
		return null;
	}

	/**
	 *
	 * @return the name of the candidate who won the election. If two or more
	 *         candidates got the same number of maximum votes, return the
	 *         TIE_RESULT field. Return null if there are no candidates.
	 */
	public String getWinner() {
		int highest = 0;
		for(String key: oversikt.keySet()) {
			if(oversikt.get(key) > highest) {
				winner = key;
				highest = oversikt.get(key);
			} else if (oversikt.get(key).equals(highest)) {
				winner = "TIE";
			}
		}
		if(winner.equals(TIE_RESULT)) {
			return TIE_RESULT;
		} else if(oversikt.size()<1) {
			return null;
		}
		return winner;
	}

	public static void main(String[] args) {
		String candidate1 = "CANDIDATE1";
		String candidate2 = "CANDIDATE2";
		VoterCounter counter = new VoterCounter();
		counter.addCandidate(candidate1);
		counter.addCandidate(candidate2);
		// Should print 0
		System.out.println(counter.getNumberOfVotes(candidate1));
		counter.castVote(candidate1);
		// Should print 1;
		System.out.println(counter.getNumberOfVotes(candidate1));
		// Should print CANDIDATE 1
		System.out.println(counter.getWinner());
		counter.castVote(candidate2);
		// Should print TIE
		System.out.println(counter.getWinner());
	}
}
