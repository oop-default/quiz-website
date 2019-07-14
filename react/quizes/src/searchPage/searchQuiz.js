import React from 'react';
import "./searchQuiz.css";
export function searchQuiz(data, page, i) {
    var key = i;
    console.log(key);
    return (
        <div key={key}>
            <div id="srchAccB1">
                <div>
                    <div id="srchAccBody">
                        <label id="srchQuizTitleLbl" >{data.title}</label>
                    </div>
                </div>
                <div id= "srchDescLnl">
                    <label id="srchQuizDescrLbl">{data.description}</label>
                </div>
                <div id="quizCreator">
                    Created by : <button id="quiz-creator-button" onClick={e=>search(data.author,page)}>{data.author}</button>
                </div>
                <button id="srchQuizBtn" onClick={e => clicked(e, page)} value={data.id}>View Quiz</button>
            </div>

        </div>
    );
}

function clicked(e, page) {
    page.props.history.push("/quiz/" + e.target.value);
}

function search(txt,page) {
    page.props.history.push("/profile/" + txt);
}