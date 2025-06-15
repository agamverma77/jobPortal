import { Button, Divider } from "@mantine/core";

import { IconArrowLeft } from "@tabler/icons-react";
import { useNavigate, useParams } from "react-router-dom";
import { jobList } from "../Data/JobsData"; // ✅ Use static job data
import ApplyJobComp from "../ApplyJob/ApplyJobComp";

const ApplyJobPage = () => {
  const navigate = useNavigate();
  const { id } = useParams();

  const job = jobList.find((job: any) => job.id == id); // ✅ Find job from static list

  return (
    <div className="min-h-[90vh] bg-mine-shaft-950 font-['poppins'] p-4">
      <Divider size="xs" mb="xs" />
      <Button
        color="brightSun.4"
        mb="xs"
        onClick={() => navigate(-1)}
        leftSection={<IconArrowLeft size={20} />}
        variant="light"
      >
        Back
      </Button>

      {job && <ApplyJobComp {...job} />}
    </div>
  );
};

export default ApplyJobPage;
