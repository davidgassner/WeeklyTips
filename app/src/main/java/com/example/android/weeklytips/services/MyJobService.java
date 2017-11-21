package com.example.android.weeklytips.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class MyJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        jobFinished(jobParameters, false);
        Log.i("JobScheduler", "onStartJob: " + jobParameters.getJobId());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
