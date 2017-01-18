import React from 'react';
import ReactDOM from 'react-dom';
import Bootstrap from 'bootstrap/dist/css/bootstrap.css';
import {Router, Route, browserHistory } from 'react-router';

import CommentBox from './commentBox.js';
import Layout from './layout.js';

const app = (
    <Router history={browserHistory}>
        <Route path="/index.html" component={Layout} >
        </Route>
        <Route path="/blog" component={CommentBox} />
    </Router>
)

ReactDOM.render(app, document.getElementById("comment-box"));


