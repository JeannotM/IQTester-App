package com.example.iqtester

class Questions {
    private val questions = arrayOf(
        "Vind jij dat God bestaat?", "Vind jij dat we Socialisme verder moeten uitbreiden?", "Vind jij dat we Communisme terug moeten brengen?", "Vind jij dat Furries het recht moeten hebben om te stemmen?",
        "Moeten mensen met Autisme gewoon normaal leren doen?", "Heb jij vandaag de 5 gebeden al gedaan?", "Was Brexit rechtvaardig?", "Had Trump toch gelijk?",
        "Is klimaatverandering wel echt?", "Is de zin: 'Doe maar wat je wilt' een valstrik?", "Bestaat vrijheid van meningsuiting nog vandaag de dag nog?", "Is geld echt zo belangrijk?",
        "Geld kan geen geluk kopen, maar zou je liever huilen in een Ferrari?", "Is CyberBullying altijd slecht?", "Zijn atleten wel daadwerkelijk rolmodelen?", "Geeft ObamaCare wel echt de zorg die Amerika nodig heeft?",
        "Hoe kunnen dingen zijn als ze niet zijn?", "has anyone really been far even as decided to use even go want to do more look like?", "Hoeveel 'watt' is big dick energy?", "Hebben mensen gemiddeld minder dan 2 armen?",
        "Furries zijn dieren, daarom mag er op gejaagd worden?", "Gelden mensen rechten voor mensen met een anime profiel foto?", "Pi kan je afronden naar 3"
    )

    private val answers = arrayOf(
            "Heel erg mee eens",
            "Beetje mee eens",
            "Niet zeker",
            "Beetje mee oneens",
            "Heel erg mee oneens"
    )

    fun getQuestions(): Array<String> {
        return questions
    }

    fun getAnswers(): Array<String> {
        return answers
    }
}