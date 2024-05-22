package com.github.panarik.english_quiz.services.database

import android.util.Log
import com.github.panarik.english_quiz.services.model.QuizGroup

private const val TAG = "[QuizesData]"

class QuizesData {

    fun getQuizes(): List<QuizEntity> {

        val quizes = listOf(
            QuizEntity(
                id = "00q36nq6d9rgg0cfj4wthhwtqr",
                difficult = "B1",
                group = QuizGroup.GRAMMAR,
                topic = "Contrasting Ideas: ‘Although,’ ‘Despite,’ and Others",
                summary = "In this quiz, you will practice using contrasting ideas with words such as 'although', 'despite', and others. These words are used to show that one thing happens even though something else is true.",
                question = "The new employee was very nervous on her first day at work _______________________ she had prepared well for the interview.",
                wrong_answer_1 = "Because",
                wrong_answer_2 = "When",
                wrong_answer_3 = "While",
                right_answer = "Although"
            ),
            QuizEntity(
                id = "011w4kjshsrgj0cfjfn94vqyx8",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Conditionals: Third and Mixed",
                summary = "This quiz will test your understanding of third conditional and mixed conditionals in English grammar.",
                question = "If I had studied harder, ____________________ (to pass) the exam.",
                wrong_answer_1 = "I would pass",
                wrong_answer_2 = "I will pass",
                wrong_answer_3 = "I passed",
                right_answer = "I would have passed"
            ),
            QuizEntity(
                id = "02ecnwn3j5rgp0cfhnw9k3ytqc",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Conditionals: Zero, First, and Second",
                summary = "In this quiz, you will practice using conditionals to talk about hypothetical situations and their consequences. You will learn how to use zero, first, and second conditionals to express different types of conditions and their outcomes.",
                question = "If it rains tomorrow, _______________________.",
                wrong_answer_1 = "I will go to the beach",
                wrong_answer_2 = "I am going to the beach",
                wrong_answer_3 = "Go to the beach",
                right_answer = "I won't go to the beach"
            ),
            QuizEntity(
                id = "04swjh65cxrgj0cfj46a06vr7m",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Conditionals: Third and Mixed",
                summary = "This quiz is designed to test your understanding of the third and mixed conditionals in English grammar. You will be presented with a series of questions that require you to use these conditionals correctly.",
                question = "If it had snowed last night, we ___________ (to go) skiing today.",
                wrong_answer_1 = "would not go",
                wrong_answer_2 = "have gone",
                wrong_answer_3 = "go",
                right_answer = "would have gone"
            ),
            QuizEntity(
                id = "0fsfdtw2ksrgp0cfj54a5b3h58",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Future Forms: ‘Will,’ ‘Be Going To,’ and Present Continuous",
                summary = "In this quiz, you will practice using the future forms \"Will,\" \"Be Going To,\" and Present Continuous to talk about future plans and predictions.",
                question = "By next Friday, I ___________ (to finish) my project.",
                wrong_answer_1 = "finishing",
                wrong_answer_2 = "am finish",
                wrong_answer_3 = "have finish",
                right_answer = "will finish"
            ),
            QuizEntity(
                id = "0rhzebbr39rgj0cfj4htb9d4vw",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Conditionals: Third and Mixed",
                summary = "In this quiz, you will practice using third and mixed conditionals to talk about past hypothetical situations and their consequences.",
                question = "If I had studied harder, _______________ (to pass) the exam.",
                wrong_answer_1 = "would pass",
                wrong_answer_2 = "have passed",
                wrong_answer_3 = "will pass",
                right_answer = "would have passed"
            ),
            QuizEntity(
                id = "16c5qff3phrgj0cfjstaejq5p4",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "British English vs. American English: Vocabulary",
                summary = "Test your knowledge of British English vs. American English vocabulary!",
                question = "What is the American English equivalent of the British English word \"lift\"?",
                wrong_answer_1 = "Elevative",
                wrong_answer_2 = "Uplifter",
                wrong_answer_3 = "Raiser",
                right_answer = "Elevator"
            ),
            QuizEntity(
                id = "1d9bb99n55rgg0cfj3ftrvk22m",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Gradable and Non-gradable",
                summary = "In English, adjectives can be either gradable or non-gradable. Gradable adjectives can be modified by intensifiers such as \"very\" or \"extremely\", while non-gradable adjectives cannot. In this quiz, you will practice identifying gradable and non-gradable adjectives.",
                question = "Which of the following adjectives is non-gradable?",
                wrong_answer_1 = "Very tired",
                wrong_answer_2 = "Extremely happy",
                wrong_answer_3 = "More beautiful",
                right_answer = "Beautiful"
            ),
            QuizEntity(
                id = "1jf3h84pthrgp0cfkteb0fvh9w",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Tenses: Be going to (Predictions)",
                summary = "Practice using the \"be going to\" tense to make predictions about future events.",
                question = "What ____________________ happen if it continues to rain?",
                wrong_answer_1 = "is going to happened",
                wrong_answer_2 = "go to happen",
                wrong_answer_3 = "been going to happen",
                right_answer = "is going to happen"
            ),
            QuizEntity(
                id = "1nqwrc3ntnrgp0cfj51vhdm9n8",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Contrasting Ideas: ‘Although,’ ‘Despite,’ and Others",
                summary = "Contrasting ideas can be expressed using various conjunctions and phrases in English. This quiz will test your understanding of 'although', 'despite', and other similar expressions.",
                question = "The new employee was very nervous on his first day at work ________ he had prepared well for the interview.",
                wrong_answer_1 = "Because",
                wrong_answer_2 = "When",
                wrong_answer_3 = "Why",
                right_answer = "Although"
            ),
            QuizEntity(
                id = "1ph6y0fc4hrgp0cfj3ftzfwym4",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Different Uses of ‘Used To’",
                summary = "In this quiz, you will practice using the verb \"used to\" in different contexts. \"Used to\" is a common phrase used to talk about past habits, customs, and things that were true in the past but are not true now.",
                question = "What did you _________ (do) when you were a child?",
                wrong_answer_1 = "do",
                wrong_answer_2 = "doing",
                wrong_answer_3 = "does",
                right_answer = "used to do"
            ),
            QuizEntity(
                id = "1qkag2s4wxrgm0cfj4nv7f2cc4",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Future Forms: ‘Will,’ ‘Be Going To,’ and Present Continuous",
                summary = "In this quiz, you will practice using the future forms \"Will\", \"Be Going To\", and Present Continuous to talk about future plans and intentions.",
                question = "By next summer, I ____________________ (to travel) to Europe.",
                wrong_answer_1 = "am traveling",
                wrong_answer_2 = "will be traveling",
                wrong_answer_3 = "be traveling",
                right_answer = "will travel"
            ),
            QuizEntity(
                id = "1x75sp9rahrgm0cfktf8pggh5w",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Tenses: Present Perfect and Past Simple",
                summary = "In this quiz, you will practice using the Present Perfect and Past Simple tenses to talk about completed actions in the past.",
                question = "I ________ (to live) in Paris for three years before I moved to London.",
                wrong_answer_1 = "live",
                wrong_answer_2 = "lived",
                wrong_answer_3 = "have lived",
                right_answer = "had lived"
            ),
            QuizEntity(
                id = "1zw0wdm60hrgj0cfk8yvjf8rhm",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Words: conjunctions",
                summary = "Practice using conjunctions to connect words, phrases, or clauses in a sentence.",
                question = "Choose the correct conjunction to complete the sentence: \"I have visited many cities in my life, _______________ Paris is my favorite.\"",
                wrong_answer_1 = "although it is very expensive",
                wrong_answer_2 = "because I have never been there",
                wrong_answer_3 = "unless you speak French",
                right_answer = "but"
            ),
            QuizEntity(
                id = "215d3yac3drgm0cfjrkrgt75wm",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Sentence Structure: words order in Interrogative Sentences",
                summary = "In this quiz, you will practice rearranging words to form correct interrogative sentences in English.",
                question = "Rearrange the words to form a correct interrogative sentence: \"what / you / are / doing\"",
                wrong_answer_1 = "You are doing what?",
                wrong_answer_2 = "What you are doing?",
                wrong_answer_3 = "Doing what you are?",
                right_answer = "What are you doing?"
            ),
            QuizEntity(
                id = "21rs13eb0nrgg0cfj43s52vvzw",
                group = QuizGroup.GRAMMAR,
                difficult = "B1",
                topic = "Future Forms: ‘Will,’ ‘Be Going To,’ and Present Continuous",
                summary = "In this quiz, you will practice using the future forms \"Will\", \"Be Going To\", and Present Continuous to talk about future plans and predictions.",
                question = "By next year, I ____________________ (to live) in a new apartment.",
                wrong_answer_1 = "am living",
                wrong_answer_2 = "will be living",
                wrong_answer_3 = "have lived",
                right_answer = "will live"
            ),
            QuizEntity(
                id = "IDIOM1",
                group = QuizGroup.IDIOM,
                difficult = "B1",
                topic = "English Idioms",
                summary = "Lets test your knowledge of English Idioms",
                question = "What is the meaning of \"face the music\"?",
                wrong_answer_1 = "take the risk",
                wrong_answer_2 = "agree",
                wrong_answer_3 = "control the situation",
                right_answer = "accept responsibility"
            ),
            QuizEntity(
                id = "IDIOM2",
                group = QuizGroup.IDIOM,
                difficult = "B1",
                topic = "English Idioms",
                summary = "Lets test your knowledge of English Idioms",
                question = "What is the meaning of \"hit the nail on the head\"?",
                wrong_answer_1 = "experience",
                wrong_answer_2 = "memorize it",
                wrong_answer_3 = "accept the consequences",
                right_answer = "absolutely right"
            ),
            QuizEntity(
                id = "IDIOM3",
                group = QuizGroup.IDIOM,
                difficult = "B1",
                topic = "English Idioms",
                summary = "Lets test your knowledge of English Idioms",
                question = "What is the meaning of \"stick your neck out\"?",
                wrong_answer_1 = "watch out",
                wrong_answer_2 = "look in front of you",
                wrong_answer_3 = "get out of the way",
                right_answer = "risk it"
            ),
            QuizEntity(
                id = "IDIOM4",
                group = QuizGroup.IDIOM,
                difficult = "B1",
                topic = "English Idioms",
                summary = "Lets test your knowledge of English Idioms",
                question = "What is the meaning of \"an old hand\"?",
                wrong_answer_1 = "tired",
                wrong_answer_2 = "an old person",
                wrong_answer_3 = "lazy",
                right_answer = "experienced" //right?
            ),
            QuizEntity(
                id = "IDIOM5",
                group = QuizGroup.IDIOM,
                difficult = "B1",
                topic = "English Idioms",
                summary = "Lets test your knowledge of English Idioms",
                question = "What is the meaning of \"under your thumb\"?",
                wrong_answer_1 = "take control",
                wrong_answer_2 = "small",
                wrong_answer_3 = "crush",
                right_answer = "press"
            ),
            QuizEntity(
                id = "IDIOM6",
                group = QuizGroup.IDIOM,
                difficult = "B1",
                topic = "English Idioms",
                summary = "Lets test your knowledge of English Idioms",
                question = "As _____ as a bat",
                wrong_answer_1 = "fast",
                wrong_answer_2 = "deaf",
                wrong_answer_3 = "black",
                right_answer = "blind"
            ),
            QuizEntity(
                id = "IDIOM7",
                group = QuizGroup.IDIOM,
                difficult = "B1",
                topic = "English Idioms",
                summary = "Lets test your knowledge of English Idioms",
                question = "As _____ as a bee",
                wrong_answer_1 = "smart",
                wrong_answer_2 = "sweet",
                wrong_answer_3 = "yellow",
                right_answer = "busy"
            ),
            QuizEntity(
                id = "IDIOM8",
                group = QuizGroup.IDIOM,
                difficult = "B1",
                topic = "English Idioms",
                summary = "Lets test your knowledge of English Idioms",
                question = "As ______ as a bell",
                wrong_answer_1 = "hard",
                wrong_answer_2 = "shiny",
                wrong_answer_3 = "loud",
                right_answer = "clear"
            ),
            QuizEntity(
                id = "IDIOM9",
                group = QuizGroup.IDIOM,
                difficult = "B1",
                topic = "English Idioms",
                summary = "Lets test your knowledge of English Idioms",
                question = "As ________ as a daisy",
                wrong_answer_1 = "clean",
                wrong_answer_2 = "beautiful",
                wrong_answer_3 = "white",
                right_answer = "fresh"
            ),
            QuizEntity(
                id = "IDIOM10",
                group = QuizGroup.IDIOM,
                difficult = "B1",
                topic = "English Idioms",
                summary = "Lets test your knowledge of English Idioms",
                question = "As _______ as an eel",
                wrong_answer_1 = "wet",
                wrong_answer_2 = "tasty",
                wrong_answer_3 = "long",
                right_answer = "slippery"
            ),
            QuizEntity(
                id = "IRREGULAR_VERBS1",
                group = QuizGroup.IRREGULAR_VERBS,
                difficult = "B1",
                topic = "Irregular verbs",
                summary = "Lets test your knowledge of English irregular verbs",
                question = "Have you _____ your lost dog yet?",
                wrong_answer_1 = "find",
                wrong_answer_2 = "",
                wrong_answer_3 = "",
                right_answer = "found"
            ),
            QuizEntity(
                id = "IRREGULAR_VERBS2",
                group = QuizGroup.IRREGULAR_VERBS,
                difficult = "B1",
                topic = "Irregular verbs",
                summary = "Lets test your knowledge of English irregular verbs",
                question = "She spoke too softly. I couldn't ______ her.",
                wrong_answer_1 = "heard",
                wrong_answer_2 = "",
                wrong_answer_3 = "",
                right_answer = "hear"
            ),
            QuizEntity(
                id = "IRREGULAR_VERBS3",
                group = QuizGroup.IRREGULAR_VERBS,
                difficult = "B1",
                topic = "Irregular verbs",
                summary = "Lets test your knowledge of English irregular verbs",
                question = "Mum taught our sister how to cook,but she didn't _____ us.",
                wrong_answer_1 = "taught",
                wrong_answer_2 = "",
                wrong_answer_3 = "",
                right_answer = "teach"
            ),
            QuizEntity(
                id = "VOCABULARY1",
                group = QuizGroup.VOCABULARY,
                difficult = "B1",
                topic = "Vocabulary",
                summary = "Lets test your knowledge of English vocabulary",
                question = "Golf is played on a golf _____.",
                wrong_answer_1 = "Field",
                wrong_answer_2 = "Course",
                wrong_answer_3 = "Track",
                right_answer = "Court"
            ),
            QuizEntity(
                id = "VOCABULARY2",
                group = QuizGroup.VOCABULARY,
                difficult = "B1",
                topic = "Vocabulary",
                summary = "Lets test your knowledge of English vocabulary",
                question = "Shepherd's Pie is most famous in ______.",
                wrong_answer_1 = "Australia",
                wrong_answer_2 = "Canada",
                wrong_answer_3 = "America",
                right_answer = "England"
            ),
            QuizEntity(
                id = "VOCABULARY3",
                group = QuizGroup.VOCABULARY,
                difficult = "B1",
                topic = "Vocabulary",
                summary = "Lets test your knowledge of English vocabulary",
                question = "Ice-cream was invented in _______.",
                wrong_answer_1 = "Japan",
                wrong_answer_2 = "France",
                wrong_answer_3 = "Italy",
                right_answer = "China"
            ),
            QuizEntity(
                id = "VOCABULARY4",
                group = QuizGroup.VOCABULARY,
                difficult = "B1",
                topic = "Vocabulary",
                summary = "Lets test your knowledge of English vocabulary",
                question = "Shish-kebab is most famous in ___________.",
                wrong_answer_1 = "Europe",
                wrong_answer_2 = "Asia",
                wrong_answer_3 = "North America",
                right_answer = "Middle East"
            ),
            QuizEntity(
                id = "SLANG1",
                group = QuizGroup.SLANG,
                difficult = "B1",
                topic = "English slang",
                summary = "Lets test your knowledge of English slang",
                question = "What does “what’s up” mean?",
                wrong_answer_1 = "something going up",
                wrong_answer_2 = "what is above you",
                wrong_answer_3 = "what is upstairs",
                right_answer = "how are you"
            ),
            QuizEntity(
                id = "SLANG2",
                group = QuizGroup.SLANG,
                difficult = "B1",
                topic = "English slang",
                summary = "Lets test your knowledge of English slang",
                question = "What does “you look beat” mean?",
                wrong_answer_1 = "you look great",
                wrong_answer_2 = "you look hot",
                wrong_answer_3 = "you are dancing",
                right_answer = "you look tired"
            ),
            QuizEntity(
                id = "SLANG3",
                group = QuizGroup.SLANG,
                difficult = "B1",
                topic = "English slang",
                summary = "Lets test your knowledge of English slang",
                question = "What does “let’s hang out” mean?",
                wrong_answer_1 = "let’s go outside",
                wrong_answer_2 = "hang our stuff",
                wrong_answer_3 = "go out",
                right_answer = "spend time together"
            ),
            QuizEntity(
                id = "SLANG4",
                group = QuizGroup.SLANG,
                difficult = "B1",
                topic = "English slang",
                summary = "Lets test your knowledge of English slang",
                question = "What does “chilled out” mean?",
                wrong_answer_1 = "cold outside",
                wrong_answer_2 = "very cold",
                wrong_answer_3 = "frozen",
                right_answer = "relaxed"
            ),
            QuizEntity(
                id = "SLANG5",
                group = QuizGroup.SLANG,
                difficult = "B1",
                topic = "English slang",
                summary = "Lets test your knowledge of English slang",
                question = "What does “have a blast” mean?",
                wrong_answer_1 = "use a lot of fireworks",
                wrong_answer_2 = "ignore someone",
                wrong_answer_3 = "have a lot to eat",
                right_answer = "have a lot of fun"
            ),
            QuizEntity(
                id = "SLANG6",
                group = QuizGroup.SLANG,
                difficult = "B1",
                topic = "English slang",
                summary = "Lets test your knowledge of English slang",
                question = "What does “have a crush” mean?",
                wrong_answer_1 = "destroy",
                wrong_answer_2 = "very tired",
                wrong_answer_3 = "being depressed",
                right_answer = "really liking someone"
            ),
            QuizEntity(
                id = "SLANG7",
                group = QuizGroup.SLANG,
                difficult = "B1",
                topic = "English slang",
                summary = "Lets test your knowledge of English slang",
                question = "What does “dumped” mean?",
                wrong_answer_1 = "empty the garbage",
                wrong_answer_2 = "to break up with someone",
                wrong_answer_3 = "to throw something away",
                right_answer = "drop something"
            ),
            QuizEntity(
                id = "SLANG8",
                group = QuizGroup.SLANG,
                difficult = "B1",
                topic = "English slang",
                summary = "Lets test your knowledge of English slang",
                question = "What does “looker” mean?",
                wrong_answer_1 = "watcher",
                wrong_answer_2 = "staring",
                wrong_answer_3 = "witness",
                right_answer = "handsome/beautiful/pretty"
            ),
            QuizEntity(
                id = "SLANG9",
                group = QuizGroup.SLANG,
                difficult = "B1",
                topic = "English slang",
                summary = "Lets test your knowledge of English slang",
                question = "What does “rip-off” mean?",
                wrong_answer_1 = "take off",
                wrong_answer_2 = "in peace",
                wrong_answer_3 = "fly off",
                right_answer = "cheat"
            ),
            QuizEntity(
                id = "SLANG10",
                group = QuizGroup.SLANG,
                difficult = "B1",
                topic = "English slang",
                summary = "Lets test your knowledge of English slang",
                question = "What does “kicks” mean?",
                wrong_answer_1 = "walks quickly",
                wrong_answer_2 = "hard",
                wrong_answer_3 = "angry",
                right_answer = "get rid of"
            ),
            QuizEntity(
                id = "SLANG11",
                group = QuizGroup.SLANG,
                difficult = "B1",
                topic = "English slang",
                summary = "Lets test your knowledge of English slang",
                question = "",
                wrong_answer_1 = "",
                wrong_answer_2 = "",
                wrong_answer_3 = "",
                right_answer = ""
            ),
        )
        Log.d(TAG, "Exporting ${quizes.size} Quizes.")
        return quizes
    }

}