import React from "react";

class TutorAuthenticationFilter extends React.Component {
  state = {
    provinces_cities: [],
    wards_districts: [],
  };

  componentDidMount() {}

  render() {
    return (
      <form action="localhost:8080/api/filter-for-tutors-authentication" method="GET">
        <div className="row">
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
              id="tutor-username"
              name="tutor_username"
              placeholder="Enter Tutor Username"
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

export default TutorAuthenticationFilter;
