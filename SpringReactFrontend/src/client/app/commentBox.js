import React from 'react';
import ReactDOM from 'react-dom';
import jQuery from 'jquery';

import Comment from './comment.js';
import CommentForm from './commentForm.js';
import config from './config/config.js';
import Bootstrap from 'bootstrap/dist/css/bootstrap.css';

class CommentBox extends React.Component {

    _getComments(){
       return this.state.comments.map((comment)=>{
            return(
                <Comment author={comment.author}
                         body={comment.body}
                         key={comment.id}
                         comment={comment}
                         onDelete={this._deleteComment.bind(this)}
                    />
            );
        });
    }

    _getCommentsTitle(commentsCount){
        if(commentsCount === 0){
            return "No comments yet!";
        }else if(commentsCount === 1){
            return "1 comment";
        } else {
            return `${commentsCount} comments`;
        }
    }

    _handleClick(){
        this.setState({
            showComments: !this.state.showComments
        });
    }

    _addComment(author, body){
        const comment = {
            author,
            body
        };

        jQuery.ajax ({
            url: this._url + 'api/comments',
            type: "POST",
            data: JSON.stringify(comment),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: newComment => this.setState({comments: this.state.comments.concat([newComment])})
        });

    }

    _deleteComment(comment){
        jQuery.ajax({
           method: 'DELETE',
           url: `${this._url}/api/comments/${comment.id}`
        });

        const comments = [...this.state.comments];
        const commentIndex = comments.indexOf(comment);
        comments.splice(comments, commentIndex);
        this.setState({comments});
    }
    _fetchCommands(){
        jQuery.ajax({
            method: 'GET',
            url: this._url + '/api/comments',
            success: (comments) => {
                this.setState({comments})
            }
        });
    }

    constructor(){
        super();
        this._url = config[process.env.NODE_ENV].api;

        this.state = {
            showComments: false,
            comments: []
        };
    }

    componentWillMount(){
        this._fetchCommands();
    }

    componentDidMount(){
        this._timer = setInterval(() => this._fetchCommands(), 5000);
    }

    componentWillUnmount(){
        clearInterval(this._timer);
    }

    render(){
        const comments = this._getComments();
        let commentNodes;
        let buttonText = 'Show comments';
        if(this.state.showComments){
            buttonText = 'Hide comments';
        }
        if(this.state.showComments){
            commentNodes = <div className="comment-list">{comments}</div>;
        }
        return(
            <div className="comment-box">
                <CommentForm addComment={this._addComment.bind(this)}/>
                <h3>Comments</h3>
                <button onClick={this._handleClick.bind(this)}>{buttonText}</button>
                <h4 className="comment-count">{this._getCommentsTitle(comments.length)}</h4>
                {commentNodes}
            </div>
        );
    }
}

ReactDOM.render(<CommentBox />, document.getElementById("comment-box"));