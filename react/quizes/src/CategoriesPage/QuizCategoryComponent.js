import React from 'react';
import "./QuizComponent.css"
export default function CategoryComponent(data,page,i) {
    return (
        <div className="col-md-4" key={i}>
            <div className="card mb-4 box-shadow">
                    <div className="card-body">
                    <p id = "com-p-quiz-title"className="card-text">{data.title}</p>
                    <p id="com-p-quiz-description" className="card--text">Description: {data.description}</p>
                    <p id="com-p-quiz-author" className="card--text">Created By: <button id="authorClickButton" onClick={() => { viewQuizCreatorProfile(data.author) }}>{data.author}</button></p>
						<div className="d-flex justify-content-between align-items-center">
                        <div className="btn-group">
                            <button type="button" className="btn btn-sm btn-outline-secondary" onClick={e => viewQuizClicked(page, data.id)}>View</button>
                            </div>
                        </div>
                    </div>
              </div>
            </div>   

    );

    function viewQuizClicked(page, data) {
        page.props.history.push("/quiz/" + data);
    }
    function viewQuizCreatorProfile(data) {
        page.props.history.push("/profile/" + data);
    }
}
/*

*/