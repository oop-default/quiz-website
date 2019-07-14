import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import * as serviceWorker from './serviceWorker';
import Loginpage from './loginpage/js/Loginpage';
import Homepage from './homepage/js/Homepage';
import notfound from './notfound'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Container from './profilepage/js/Container';
import QuizPage from './QuizBody/js/QuizPage';
import searchPage from './searchPage/searchPage';
import CreateQuiz from './createQuiz/js/CreateQuiz';


const routing = (
    <Router>
        <Switch>
            <Route path="/" exact component={Homepage} />
            <Route path="/login" exact component={Loginpage} />
            <Route path="/profile/:userId" exact component={Container} />
            <Route path="/quiz/:quizId" exact component={QuizPage} />
            <Route path="/search/:searchQuery" exact component={searchPage}/>
            <Route path="/createQuiz" exact component={CreateQuiz}/>
            <Route component={notfound} />
        </Switch>
    </Router>
    );

ReactDOM.render(routing, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
