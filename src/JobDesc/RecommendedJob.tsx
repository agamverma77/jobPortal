import { useParams } from "react-router-dom";

import JobCard from "../FindJobs/JobCard";
import { jobList } from "../Data/JobsData";

const RecommendedJob = () => {
  const { id } = useParams();

  return (
    <div>
      <div className="text-xl font-semibold mb-5">Recommended Job</div>
      <div className="flex bs:flex-col flex-wrap gap-5 justify-between bs-mx:justify-start">
        {jobList.map(
          (job: any, index: number) =>
            index < 6 && job.id != id && <JobCard key={index} {...job} />
        )}
      </div>
    </div>
  );
};

export default RecommendedJob;
