import { ActionIcon, Button, Divider } from "@mantine/core";

import { IconBookmark, IconBookmarkFilled } from "@tabler/icons-react";
import { Link } from "react-router-dom";
import { card } from "../Data/JobDescData";

const Job = () => {
  const cleanHTML = `<h4>Job Description</h4><p>This is a sample job description.</p><ul><li>Requirement 1</li><li>Requirement 2</li></ul>`;

  return (
    <div data-aos="zoom-out" className="w-2/3 bs-mx:w-full">
      <div className="flex justify-between items-center flex-wrap">
        <div className="flex items-center gap-2">
          <div className="p-3 bg-mine-shaft-800 rounded-xl shrink-0 flex ">
            <img className="h-14 xs-mx:h-10 xs-mx:w-10" src={`/Icons/Google.png`} alt="" />
          </div>
          <div className="flex flex-col gap-1">
            <div className="font-semibold text-2xl xs-mx:text-xl">Software Engineer</div>
            <div className="text-lg text-mine-shaft-300 flex flex-wrap xs-mx:text-base">
              <span>Google &bull; </span>
              <span>3 days ago &bull; </span>
              <span>42 Applicants</span>
            </div>
          </div>
        </div>

        <div className="flex sm:flex-col gap-2 items-center sm-mx:my-5 sm-mx:w-full sm-mx:[&>button]:w-1/2">
          <Link to={`/apply-job/sample-id`}>
            <Button color="brightSun.4" size="sm" variant="light">Apply</Button>
          </Link>
          <IconBookmark className="cursor-pointer hover:text-bright-sun-400  text-mine-shaft-300" stroke={1.5} />
        </div>
      </div>

      <Divider size="xs" my="xl" />

      <div className="flex justify-between gap-4 sm-mx:flex-wrap">
        {card.map((item, index) => (
          <div key={index} className="flex flex-col text-sm gap-1 items-center ">
            <ActionIcon className="!h-12 !w-12 xs-mx:!h-8 xs-mx:!w-8" variant="light" color="brightSun.4" radius="xl">
              <item.icon className="h-4/5 w-4/5" />
            </ActionIcon>
            <div className="text-mine-shaft-300 xs-mx:text-sm">{item.name}</div>
            <div className="text-base font-semibold xs-mx:text-sm">NA{item.id === "packageOffered" && <> LPA</>}</div>
          </div>
        ))}
      </div>

      <Divider size="xs" my="xl" />

      <div>
        <div className="text-xl font-semibold mb-5">Required Skills</div>
        <div className="flex flex-wrap gap-2">
          <ActionIcon className="!h-fit !w-fit font-medium !text-sm xs-mx:!text-xs" variant="light" color="brightSun.4" p="xs" radius="xl">JavaScript</ActionIcon>
          <ActionIcon className="!h-fit !w-fit font-medium !text-sm xs-mx:!text-xs" variant="light" color="brightSun.4" p="xs" radius="xl">React</ActionIcon>
        </div>
      </div>

      <Divider size="xs" my="xl" />

      <div className="[&>h4]:text-xl [&_*]:text-mine-shaft-300 [&_li]:marker:text-bright-sun-300 [&_li]:mb-1 [&>h4]:text-mine-shaft-200 [&>h4]:font-semibold [&>h4]:my-5 [&_p]:text-justify [&_p]:text-sm [&_li]:text-sm" dangerouslySetInnerHTML={{ __html: cleanHTML }} />

      <Divider size="xs" my="xl" />

      <div>
        <div className="text-xl font-semibold mb-5">About Company</div>
        <div className="flex items-center justify-between mb-3 xs-mx:flex-wrap xs-mx:gap-2">
          <div className="flex items-center gap-2">
            <div className="p-3 bg-mine-shaft-800 rounded-xl flex ">
              <img className="h-8" src={`/Icons/Google.png`} alt="" />
            </div>
            <div>
              <div className="text-lg font-medium">Google</div>
              <div className="text-mine-shaft-300">10k+ Employees</div>
            </div>
          </div>
          <Link to={`/company/Google`}>
            <Button color="brightSun.4" variant="light">Company Page</Button>
          </Link>
        </div>
        <div className="text-mine-shaft-300 text-justify xs-mx:text-sm">
          Lorem ipsum dolor sit amet consectetur, adipisicing elit. Quo fuga recusandae perferendis, excepturi nostrum debitis...
        </div>
      </div>
    </div>
  );
};

export default Job;
