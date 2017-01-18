import React from 'react';
import ReactDOM from 'react-dom';
import Bootstrap from 'bootstrap/dist/css/bootstrap.css';
import {Router, Route, Redirect, browserHistory } from 'react-router';

import CommentBox from './commentBox.js';
import Layout from './layout.js';

const app = (
    <Router history={browserHistory}>
        <Redirect from="/index.html" to="/" />
        <Route path="/" component={Layout} >
            <Route path="blog" component={CommentBox} />
        </Route>
    </Router>
)

ReactDOM.render(app, document.getElementById("comment-box"));


