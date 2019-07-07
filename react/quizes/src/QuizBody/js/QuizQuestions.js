import React from 'react';

export function AnswerComponent(data,pos){
    var answerType = data.type;
    switch(answerType){
        case "QuestionResponse" : return(QuestionResponseAnswer(pos,data));
        case "MultipleChoice" : return(MultipleChoiceAnswer(pos,data));
        case "PictureResponseQuestions" : return(PictureResponseQuestionsAnswer(pos,data));
        case "FillInTheBlank" : return(FillInTheBlankAnswer(pos,data));
        default : return(QuestionResponseAnswer(pos,data));
    }
}
function QuestionResponseAnswer(pos,data){
    var answer = document.getElementById(pos+"input").value;
    var realAnswer = data.correct;
    return(
       answer === realAnswer
    );
}
function MultipleChoiceAnswer(pos,data){
    var answerSize = data.correct.length;
    let button;

    for (let index = 0; index <= answerSize; index++) {
        button = document.getElementById(pos+"input"+index);
        if(button === null)continue;
        if(button.checked){
            return(button.value === data.correct);
        }
    }
    return(false);
}
function PictureResponseQuestionsAnswer(pos,data){
    var answer = document.getElementById(pos+"input").value;
    var realAnswer = data.correct;
    return(
       answer === realAnswer
    );
}
function FillInTheBlankAnswer(pos,data){
    var answer = document.getElementById(pos+"input").value;
    var realAnswer = data.correct;
    return(
       answer === realAnswer
    );
}

//------------------------------------------------------------------------------------
export function QuestionComponent(data){
    var questionType = data.type;
    switch(questionType){
        case "QuestionResponse" : return(QuestionResponse(data));
        case "MultipleChoice" : return(MultipleChoice(data));
        case "PictureResponseQuestions" : return(PictureResponseQuestions(data));
        case "MultiAnswerQuestions" : return(MultiAnswerQuestions(data));
        default : return(FillInTheBlank(data));
    }
}
function QuestionResponse(data){
        return(
            <div id = {data.ID+"question"} className = "QuizContent">
            <h3 className = "questionText">{data.question}</h3>
            <div>Insert your answer here :  <textarea id = {data.ID+"input"} className = "QuizInput" /></div>
            </div>
        );
}
function MultipleChoice(data){
    return(
        <div id = {data.ID+"question"} className = "QuizContent">
        <h3 className = "questionText">{data.question}</h3>
        {data.answers.map((answer,index) => <div><input id = {data.ID+"input"+index} type = "radio" name = {"group"+data.ID} className = "introtext" value = {answer} />{answer}</div>)}
        </div>
    );
}
function PictureResponseQuestions(data){
    return(
        <div id = {data.ID+"question"} className = "QuizContent">
        <h3 className = "questionText">{data.question}</h3>
        <div className = "questionText"><img style = {{position : "unset"}} src = {require("./multiple-choice.jpg")} className = "questionImage"/></div>
        <div>Insert your answer here :  <textarea id = {data.ID+"input"} className = "QuizInput" /></div>
        </div>
    );
}
function MultiAnswerQuestions(data){
    return(
        <div id = {data.ID+"question"} className = "QuizContent">
        <h3 className = "questionText">{data.question}</h3>
        {data.correct.map((answer,index) => <textarea id = {data.ID + "input"+index} className = "QuizInput" />)}
        </div>
    );
}
function FillInTheBlank(data){
    return(
        <div id = {data.ID+"question"} className = "QuizContent">
        <h3 className = "questionText">{data.question}</h3>
        <div>Insert your answer here :  <textarea id = {data.ID+"input"} className = "QuizInput" /></div>
        </div>
    );
}