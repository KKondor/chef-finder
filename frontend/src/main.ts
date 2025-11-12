import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from '../../../chef-finder-fe/src/app/app.config';
import { App } from '../../../chef-finder-fe/src/app/app';

bootstrapApplication(App, appConfig)
  .catch((err) => console.error(err));
