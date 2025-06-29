import { Button, NumberInput, TagsInput, Textarea } from "@mantine/core";

import SelectInput from "./SelectInput";
import { isNotEmpty, useForm } from "@mantine/form";
import { successNotification } from "../../Services/NotificationService";
import { useNavigate, useParams } from "react-router-dom";
import { useSelector } from "react-redux";
import { useEffect, useState } from "react";


import TextEditor from "./TextEditor";

import { content, fields } from "../../Data/PostJob";
import { jobList } from "../../Data/JobsData";

const PostJob = () => {
  const { id } = useParams();
  const user = useSelector((state: any) => state.user);
  const navigate = useNavigate();
  const select = fields;
  const [editorData, setEditorData] = useState(content);

  const form = useForm({
    mode: "controlled",
    validateInputOnChange: true,
    initialValues: {
      jobTitle: "",
      company: "",
      experience: "",
      jobType: "",
      location: "",
      packageOffered: "",
      skillsRequired: [],
      about: "",
      description: content,
    },
    validate: {
      jobTitle: isNotEmpty("Title cannot be empty"),
      company: isNotEmpty("Company cannot be empty"),
      location: isNotEmpty("Location cannot be empty"),
      about: isNotEmpty("About cannot be empty"),
      description: isNotEmpty("Description cannot be empty"),
      experience: isNotEmpty("Experience cannot be empty"),
      jobType: isNotEmpty("Job Type cannot be empty"),
      packageOffered: isNotEmpty("Salary cannot be empty"),
      skillsRequired: isNotEmpty("Skills cannot be empty"),
    },
  });

  useEffect(() => {
    window.scrollTo(0, 0);
    if (Number(id) !== 0) {
      const job = jobList.find((job: any) => job.id == id);
      if (job) {
        form.setValues(job);
        setEditorData(job.description);
      }
    } else {
      form.reset();
    }
  }, [id]);

  const handlePost = () => {
    form.validate();
    if (!form.isValid()) {
      window.scrollTo({ top: 0, behavior: "smooth" });
      return;
    }

    // Just console log since no backend
    console.log("Published Job: ", {
      ...form.getValues(),
      id,
      postedBy: user?.id || "dummy-user",
      jobStatus: "ACTIVE",
    });

    successNotification("Success", "Job Published Successfully");
    navigate("/posted-jobs/1"); // Static redirect
  };

  const handleDraft = () => {
    console.log("Draft Saved: ", {
      ...form.getValues(),
      id,
      postedBy: user?.id || "dummy-user",
      jobStatus: "DRAFT",
    });

    successNotification("Success", "Job Saved as Draft");
    navigate("/posted-jobs/1");
  };

  return (
    <div data-aos="zoom-out" className="px-16 bs-mx:px-10 md-mx:px-5 py-5 ">
      <div className="text-2xl font-semibold mb-5">Post a Job</div>
      <div className="flex flex-col gap-5">
        <div className="flex gap-10 md-mx:gap-5 [&>*]:w-1/2 sm-mx:[&>*]:!w-full sm-mx:flex-wrap">
          <SelectInput form={form} name="jobTitle" {...select[0]} />
          <SelectInput form={form} name="company" {...select[1]} />
        </div>
        <div className="flex gap-10 md-mx:gap-5 [&>*]:w-1/2 sm-mx:[&>*]:!w-full sm-mx:flex-wrap">
          <SelectInput form={form} name="experience" {...select[2]} />
          <SelectInput form={form} name="jobType" {...select[3]} />
        </div>
        <div className="flex gap-10 md-mx:gap-5 [&>*]:w-1/2 sm-mx:[&>*]:!w-full sm-mx:flex-wrap">
          <SelectInput form={form} name="location" {...select[4]} />
          <NumberInput
            data-aos="zoom-out"
            {...form.getInputProps("packageOffered")}
            withAsterisk
            label="Salary (LPA)"
            placeholder="Enter Salary"
            hideControls
            min={1}
            max={300}
            clampBehavior="strict"
          />
        </div>
        <TagsInput
          data-aos="zoom-out"
          {...form.getInputProps("skillsRequired")}
          withAsterisk
          label="Skills"
          placeholder="Enter skill"
          splitChars={[",", " ", "|"]}
          clearable
        />
        <Textarea
          data-aos="zoom-out"
          {...form.getInputProps("about")}
          withAsterisk
          className="my-3"
          label="About Job"
          autosize
          minRows={2}
          placeholder="Enter about job.."
        />
        <div className="[&_button[data-active='true']]:!text-bright-sun-400 [&_button[data-active='true']]:!bg-bright-sun-400/20">
          <div className="text-sm font-medium ">
            Job Description<span className="text-red-600 "> *</span>
          </div>
          <TextEditor data-aos="zoom-out" form={form} data={editorData} />
        </div>
        <div className="flex gap-4">
          <Button
            data-aos="zoom-out"
            color="brightSun.4"
            onClick={handlePost}
            variant="light"
          >
            Publish Job
          </Button>
          <Button
            data-aos="zoom-out"
            color="brightSun.4"
            onClick={handleDraft}
            variant="outline"
          >
            Save as Draft
          </Button>
        </div>
      </div>
    </div>
  );
};

export default PostJob;
