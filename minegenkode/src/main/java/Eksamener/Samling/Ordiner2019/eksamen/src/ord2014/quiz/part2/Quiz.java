package ord2014.quiz.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import ord2014.quiz.part1.Question;

public class Quiz {

	private Collection<Question> questions = new ArrayList<Question>();
	
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
	public void init(Reader input) throws IOException {
		BufferedReader reader = new BufferedReader(input);
		while (reader.ready()) {
			String question = reader.readLine();
			if (question == null || question.trim().length() == 0) {
				break;
			}
			String answer = reader.readLine();
			Collection<String> options = new ArrayList<String>();
			while (reader.ready()) {
				String line = reader.readLine();
				if (line == null || line.trim().length() == 0) {
					break;
				}
				options.add(line);
			}
			addQuestion(new Question(question, answer, options));
		}
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		for (Question question : questions) {
			question.askQuestion(System.out);
			while (scanner.hasNextLine()) {
				String answer = scanner.nextLine();
				if (question.checkAnswer(answer)) {
					break;
				}
				System.out.println("Feil, prøv igjen");
			}
		}
		scanner.close();
	}

	public static void main(String[] args) {
		Quiz quiz = new Quiz();
		try {
			quiz.init(new InputStreamReader(quiz.getClass().getResourceAsStream("sample.txt")));
			quiz.run();
		} catch (IOException e) {
		}
	}
}
