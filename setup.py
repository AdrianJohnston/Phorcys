from setuptools import setup, find_packages

setup(
    name='Phorcys',
    version='0.1dev',
    package=['Phorcys'],
    package_dir=find_packages('Phorcys'),
    requires=['numpy'],
    # include_package_data=True,
)
