import React from 'react';

class Comment extends React.Component {

    render(){
        return(
            <div className="comment">
                <p className="comment-header">{this.props.author}</p>
                <p className="comment-body">
                    {this.props.body}
                </p>
                <div className="comment-footer">
                    <a href="#" onClick={this._handleDelete.bind(this)}>Delete Comment</a>
                </div>
            </div>
        );
    }

    _handleDelete(event){
        event.preventDefault();
        if(confirm('Are you sure ?')) {
            this.props.onDelete(this.props.comment);
        }
    }
}

export default Comment
