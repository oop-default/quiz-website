import React, { Component } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import Loginpage from './loginpage/js/Loginpage';
import Homepage from './homepage/js/Homepage';
class QuizSite extends Component {
    constructor() {
        super();
        console.log();
    }
    render() {
        return (
            <Router>
                <Route path="/" Component={Homepage}/>
            </Router>
            );
    }
}
export default QuizSite;