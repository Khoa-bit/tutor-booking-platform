import React from "react";

class ClassFilter extends React.Component {
  state = {
    classes: []
  };

  componentDidMount() {}

  render() {
    return (
      <form action="">
        <div className="row">
          <div className="col-3">
            <input
              className="form-control"
              id="class-id"
              name="class_id"
              placeholder="Enter Class ID"
              type="text"
            />
          </div>
          <div className="col-3">
            <input
              className="form-control"
              id="tutor-id"
              name="tutor_id"
              placeholder="Enter Tutor ID"
              type="text"
            />
          </div>
          <div className="col-3">
          <input
              className="form-control"
              id="student-id"
              name="student_id"
              placeholder="Enter Student ID"
              type="text"
            />
          </div>
          <div className="col-1 d-flex justify-content-end">
            <button
              className="btn btn-primary"
              style={{ width: "100%" }}
              type="submit"
            >
              Search
            </button>
          </div>
        </div>
      </form>
    );
  }
}

export default ClassFilter;
