import React from 'react';
import "./searchQuiz.css";
export function searchQuiz(data, page) {
    return (
        <div>
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
                    Created by : {data.author}
                </div>
                <button id="srchQuizBtn" onClick={e => clicked(e, page)} value={data.id}>View Quiz</button>
            </div>

        </div>
    );
}

function clicked(e, page) {
    page.props.history.push("/quiz/" + e.target.value);
}