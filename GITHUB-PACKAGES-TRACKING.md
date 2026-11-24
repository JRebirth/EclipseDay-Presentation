# GitHub Packages tracking

Workflow `.github/workflows/snapshot.yml` is **`workflow_dispatch` only**.

Reason: legacy app/presentation. Properties were bumped toward JRebirth 12.x / RIA 4.x where applicable, but the codebase may need compile fixes before enabling `push` CI.

After a successful manual run, switch the workflow `on:` to include `push: branches: [master]`.